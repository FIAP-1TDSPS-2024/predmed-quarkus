package br.com.fiap.service;

import br.com.fiap.dto.TriagemDTO;
import br.com.fiap.model.Diagnostico;
import br.com.fiap.model.Paciente;
import br.com.fiap.model.Triagem;
import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@ApplicationScoped
public class DiagnosticoService {

    @Inject
    TriagemService triagemService;

    public Diagnostico findById(int id){

        return (Diagnostico) Diagnostico.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Diagnostico em questão não foi encontrado"));
    }

    public Diagnostico gerarDiagnostico(int triagemId){
        Diagnostico diagnostico = null;

        Triagem triagem = triagemService.findById(triagemId);

        String url = "";

        //request
        HttpGet request = new HttpGet(url);

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Criando um objeto Gson
            Gson gson = new Gson();

            diagnostico = gson.fromJson(result, Diagnostico.class);
        }
        else{
            System.out.println("Erro na requisição");
        }

        return diagnostico;
    }
}
