package com.example.smartnutri;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import Model.Nutricionista;
import Model.Paciente;
import Repository.NutricionistaRepository;
import Repository.PacienteRepository;
import View.LoginActivity;
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class LoginActivityTest {

    private LoginActivity loginActivity;

    @Before
    public void setUp() {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }

    @Test
    public void testLoginWithValidCredentials() throws Exception {

        List<Paciente> pacientes = new ArrayList<>();
        List<Nutricionista> nutris = new ArrayList<>();

        Paciente p1 = new Paciente("p1","469.311.830-36","08/07/2000","4799999999","p1@gmail.com","p1@12345",65.0,170.0,"M","teste1");
        Paciente p2 = new Paciente("p2","497.801.200-76","08/07/2000","4799999999","p2@gmail.com","p2@12345",65.0,170.0,"F","teste2");
        Paciente p3 = new Paciente("p3","660.906.460-16","08/07/2000","4799999999","p3@gmail.com","p3@12345",65.0,170.0,"M","teste3");

        Nutricionista n1 = new Nutricionista("n1", "829.423.990-90", "11/10/1997", "47988888888", "n1@gmail.com", "n1@123", "5548676");
        Nutricionista n2 = new Nutricionista("n2", "299.516.280-01", "11/10/1997", "47988888888", "n2@gmail.com", "n2@123", "547886");

        pacientes.add(p1);
        pacientes.add(p2);
        pacientes.add(p3);
        nutris.add(n1);
        nutris.add(n2);


        // Mockando os repositórios e dados necessários para o teste
        PacienteRepository pacienteRepositoryMock = mock(PacienteRepository.class);
        NutricionistaRepository nutricionistaRepositoryMock = mock(NutricionistaRepository.class);

        when(pacienteRepositoryMock.getAllPacientes()).thenReturn(pacientes);
        when(nutricionistaRepositoryMock.getAllNutricionistas()).thenReturn(nutris);

        loginActivity.pacienteRepository = pacienteRepositoryMock;
        loginActivity.nutricionistaRepository = nutricionistaRepositoryMock;

        // Configurar um paciente válido
        Paciente pacienteValido = new Paciente();
        pacienteValido.setEmail("p1@gmail.com");
        pacienteValido.setSenha("p1@12345");

        when(pacienteRepositoryMock.getPacienteByEmail(anyString())).thenReturn(pacienteValido);

        // Configurar a interface do usuário
        loginActivity.edtxEmail.setText("email@paciente.com");
        loginActivity.edtxSenha.setText("senha");

        // Executar o método de login
        int result = loginActivity.login("email@paciente.com", "senha");

        // Verificar se o login foi bem-sucedido
        assertEquals(1, result);
    }

    @Test
    public void testLoginWithEmptyPassword() throws Exception {
        // Configurar a interface do usuário com senha em branco
        loginActivity.edtxEmail.setText("email@paciente.com");
        loginActivity.edtxSenha.setText("");

        // Executar o método de login
        int result = loginActivity.login("email@paciente.com", "");

        // Verificar se o login falhou (deve retornar 0)
        assertEquals(0, result);
    }

    @Test
    public void testLoginWithInvalidEmail() throws Exception {
        // Configurar a interface do usuário com e-mail inválido
        loginActivity.edtxEmail.setText("email_invalido");
        loginActivity.edtxSenha.setText("senha");

        // Executar o método de login
        int result = loginActivity.login("email_invalido", "senha");

        // Verificar se o login falhou (deve retornar 0)
        assertEquals(0, result);
    }
}
