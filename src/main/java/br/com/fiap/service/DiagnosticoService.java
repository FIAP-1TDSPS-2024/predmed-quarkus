package br.com.fiap.service;

import br.com.fiap.dto.DiagnosticoDTO;
import br.com.fiap.dto.TriagemDTO;
import br.com.fiap.dto.TriagemRequestDTO;
import br.com.fiap.model.Diagnostico;
import br.com.fiap.model.Paciente;
import br.com.fiap.model.Triagem;
import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class DiagnosticoService {

    @Inject
    TriagemService triagemService;

    public Diagnostico findById(int id){

        return (Diagnostico) Diagnostico.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Diagnostico em questão não foi encontrado"));
    }

    @Transactional
    public Diagnostico save(Diagnostico diagnostico){
        Diagnostico.persist(diagnostico);
        return diagnostico;
    }

    public Diagnostico gerarDiagnostico(int triagemId){

        DiagnosticoDTO diagnosticoDTO = null;
        Diagnostico diagnostico = null;

        Triagem triagem = triagemService.findById(triagemId);
        TriagemRequestDTO triagemRequestDTO = new TriagemRequestDTO(triagem);

        String url = "https://elaborate-ermengarde-jonasjose1479-1ec9b708.koyeb.app/predict_covid";

        //Criando um objeto Gson
        Gson gson = new Gson();

        //request
        HttpPost request = new HttpPost(url);
        String jsonBody = gson.toJson(triagemRequestDTO);

        //entity
        StringEntity StringEntity = new StringEntity(jsonBody, StandardCharsets.UTF_8);
        StringEntity.setContentType("application/json");
        request.setEntity(StringEntity);

        //client
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();

        //response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpEntity entity = response.getEntity();

        if (entity != null){
            String result = null;
            try {
                result = EntityUtils.toString(entity);
                System.out.println(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            diagnosticoDTO = gson.fromJson(result, DiagnosticoDTO.class);
            diagnostico = new Diagnostico(diagnosticoDTO, triagem);
            save(diagnostico);
        }
        else{
            System.out.println("Erro na requisição");
        }

        return diagnostico;
    }
}
