package com.example.gsonhttp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {
    public InputStream obterRespostaHttp(String endereco) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            return new BufferedInputStream(conexao.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
