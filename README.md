Como executar a API do Teste - Capgemini:

A API possui dois endpoints, "/sequence" e "/stats" dos tipos POST e GET respectivamente.

A API funciona apenas de forma local, logo, é necessário executar o arquivo "TesteApplication.java" (onde se encontra a função Main) como uma aplicação Java.

Após a execução, os endpoints podem ser acessados em "http://localhost:8080/sequence" ou "http://localhost:8080/stats".

Por não possuir uma interface de usuário, o acesso deve ser feito através de uma plataforma de API como o Postman.

Para o endpoint "/sequence" é preciso passar no Body um JSON que representa a Sequencia a ser verificada, segue exemplo:

{

"letters": ["DUHBHB", "DUBUHD", "UBUUHU", "BHBDHH", "DDDDUB", "UDBDUH"]

}

Para o endpoint "/stats" não é preciso passar nada e a resposta já será fornecida.

O endpoint "/stats" vai considerar todas as Sequencias presentes no banco, inclusive as que o desenvolvedor utilizou em seus próprios testes, logo os números
na resposta do endpoint devem ser altos.