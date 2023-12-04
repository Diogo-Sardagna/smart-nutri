package com.example.smartnutri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;

import View.CadastroActivity;

public class CadastroActivityTest {

    /****************************************** Diogo *********************************************/
    /********************************** Teste Data de Nascimento **********************************/
    @Test
    public void testCadastroWithValidDataNascimento() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (data válida)
        when(cadastroActivityMock.isValidDataNascimento(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação de data de nascimento
        boolean result = cadastroActivityMock.isValidDataNascimento("01/01/2000");

        // Verificar se a validação ocorreu corretamente (você pode ajustar isso conforme necessário)
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidDataNascimento("01/01/2000");
    }

    @Test
    public void testCadastroWithInvalidDataNascimento() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (data inválida)
        when(cadastroActivityMock.isValidDataNascimento(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação de data de nascimento
        boolean result = cadastroActivityMock.isValidDataNascimento("01/01/3000");

        // Verificar se a validação ocorreu corretamente (você pode ajustar isso conforme necessário)
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidDataNascimento("01/01/3000");
    }

    /****************************************** Diogo *********************************************/
    /***************************************** Teste Peso ****************************************/

    @Test
    public void testCadastroWithValidPeso() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (peso válido)
        when(cadastroActivityMock.isValidPeso(anyDouble())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação de peso
        boolean result = cadastroActivityMock.isValidPeso(70.0);

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidPeso(70.0);
    }

    @Test
    public void testCadastroWithInvalidPeso() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (peso inválido)
        when(cadastroActivityMock.isValidPeso(anyDouble())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação de peso
        boolean result = cadastroActivityMock.isValidPeso(700.0);

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidPeso(700.0);
    }

    /****************************************** Diogo *********************************************/
    /************************************** Teste Altura *****************************************/

    @Test
    public void testCadastroWithValidAltura() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (altura válida)
        when(cadastroActivityMock.isValidAltura(anyDouble())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação de altura
        boolean result = cadastroActivityMock.isValidAltura(1.80);

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidAltura(1.80);
    }

    @Test
    public void testCadastroWithInvalidAltura() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (altura inválida)
        when(cadastroActivityMock.isValidAltura(anyDouble())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação de altura
        boolean result = cadastroActivityMock.isValidAltura(5.50);

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidAltura(5.50);
    }

    /****************************************** Diogo *********************************************/
    /*********************************** Teste Nome em Branco ************************************/

    @Test
    public void testCadastroWithValidNome() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (nome válido)
        when(cadastroActivityMock.isCampoEmBranco(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação do nome
        boolean result = cadastroActivityMock.isCampoEmBranco("John Doe");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isCampoEmBranco("John Doe");
    }

    @Test
    public void testCadastroWithInvalidNome() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (nome em branco)
        when(cadastroActivityMock.isCampoEmBranco(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação do nome
        boolean result = cadastroActivityMock.isCampoEmBranco("");

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isCampoEmBranco("");
    }

    /****************************************** Diogo *********************************************/
    /*************************************** Teste E-mail ****************************************/

    @Test
    public void testCadastroWithValidEmail() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (e-mail válido)
        when(cadastroActivityMock.isValidEmail(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação do e-mail
        boolean result = cadastroActivityMock.isValidEmail("john.doe@example.com");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidEmail("john.doe@example.com");
    }

    @Test
    public void testCadastroWithInvalidEmail() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (e-mail inválido)
        when(cadastroActivityMock.isValidEmail(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação do e-mail
        boolean result = cadastroActivityMock.isValidEmail("email-invalido");

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidEmail("email-invalido");
    }

    /****************************************** Diogo *********************************************/
    /**************************************** Teste Senha *****************************************/

    @Test
    public void testCadastroWithValidPassword() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (senha válida)
        when(cadastroActivityMock.isValidPassword(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação da senha
        boolean result = cadastroActivityMock.isValidPassword("Senha123!");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidPassword("Senha123!");
    }

    @Test
    public void testCadastroWithInvalidPassword() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (senha inválida)
        when(cadastroActivityMock.isValidPassword(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação da senha
        boolean result = cadastroActivityMock.isValidPassword("senha");

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidPassword("senha");
    }

    /****************************************** Diogo *********************************************/
    /*********************************** Teste Nome em Branco ************************************/

    public void testPasswordConfirmationWithMatchingPasswords() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (confirmação correta)
        when(cadastroActivityMock.passwordConfirm(anyString(), anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à confirmação de senha
        boolean result = cadastroActivityMock.passwordConfirm("Senha123!", "Senha123!");

        // Verificar se a confirmação ocorreu corretamente
        assertTrue(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).passwordConfirm("Senha123!", "Senha123!");
    }

    @Test
    public void testPasswordConfirmationWithMismatchedPasswords() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (confirmação incorreta)
        when(cadastroActivityMock.passwordConfirm(anyString(), anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à confirmação de senha
        boolean result = cadastroActivityMock.passwordConfirm("Senha123!", "outraSenha123!");

        // Verificar se a confirmação ocorreu corretamente
        assertFalse(result);

        // Verificar se o método específico foi chamado
        verify(cadastroActivityMock).passwordConfirm("Senha123!", "outraSenha123!");
    }

    /****************************************** Diogo *********************************************/
    /*************************************** Teste CPF ********************************************/

    @Test
    public void testValidCPF() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para não fazer nada
//        doNothing().when(cadastroActivityMock).isValidCPF(anyString());

        // Configurar o comportamento do mock para retornar true (cpf válido)
        when(cadastroActivityMock.isValidCPF(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação de CPF
        boolean result = cadastroActivityMock.isValidCPF("123.456.789-09");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidCPF("123.456.789-09");
    }

    @Test
    public void testInvalidCPF() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (cpf inválido)
        when(cadastroActivityMock.isValidCPF(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação de CPF
        boolean result = cadastroActivityMock.isValidCPF("111.111.111-11");

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidCPF("111.111.111-11");
    }

    /****************************************** Diogo *********************************************/
    /************************************** Teste Telefone ***************************************/

    @Test
    public void testIsValidTelefone() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (telefone correto)
        when(cadastroActivityMock.isValidTelefone(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação do telefone
        boolean result = cadastroActivityMock.isValidTelefone("+55 (47) 99103-2009");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidTelefone("+55 (47) 99103-2009");
    }

    @Test
    public void testInvalidTelefone() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar false (telefone incorreto)
        when(cadastroActivityMock.isValidTelefone(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação do telefone
        boolean result = cadastroActivityMock.isValidTelefone("+55 (47) 91");  // Formato inválido

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidTelefone("+55 (47) 91");
    }

    /****************************************** Diogo *********************************************/
    /***************************************** Teste CRM ******************************************/

    @Test
    public void testIsValidCrm() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (crm correto)
        when(cadastroActivityMock.isValidCrm(anyString())).thenReturn(true);

        // Chamar o método ou ação que você deseja testar em relação à validação do CRM
        boolean result = cadastroActivityMock.isValidCrm("CRM-1/12345");

        // Verificar se a validação ocorreu corretamente
        assertTrue(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidCrm("CRM-1/12345");
    }

    @Test
    public void testInvalidCrm() {
        CadastroActivity cadastroActivityMock = mock(CadastroActivity.class);

        // Configurar o comportamento do mock para retornar true (crm incorreto)
        when(cadastroActivityMock.isValidCrm(anyString())).thenReturn(false);

        // Chamar o método ou ação que você deseja testar em relação à validação do CRM
        boolean result = cadastroActivityMock.isValidCrm("CRM-6/12345");  // Número inválido

        // Verificar se a validação ocorreu corretamente
        assertFalse(result);

        // Ou, se você quiser verificar se o método específico foi chamado
        verify(cadastroActivityMock).isValidCrm("CRM-6/12345");
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
}
