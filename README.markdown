Urls Mais Acessadas Portlet

Configurando a conta:
	Abra a classe: AnalyticsCliente.java
	localize as linhas:
		public static final String USERNAME = "x@gmail.com"; // Login do analitcs
  	public static final String PASSWORD = "x"; // Senha do analitcs
  e insira o username e password referente a conta do google analitcs.
  
  Esta linha também é importante porque é com base neste perfil que os dados serão analisados
  o atual se refere ao portal antigo.
  public static final String ACCOUNT_NAME = "www.trt23.jus.br";
  
  
Para empacotar o projeto, no eclipse clique com o mouse direito no projeto > export > war .

Deploy:
Para efetuar o deploy basta colocar na pasta {JBOSS_HOME}/server/{portal}/deploy

Qualquer dúvida:
rsilva@redhat.com
