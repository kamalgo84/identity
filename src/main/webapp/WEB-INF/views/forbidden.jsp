<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!-- build:css(.) styles/vendor.css -->
    <!-- bower:css -->
    <link rel="stylesheet" href="../public_app/bower_components/angular-material/angular-material.css" />
    <!-- endbower -->
    <!-- endbuild -->
    <!-- build:css(.tmp) styles/main.css -->
    <link rel="stylesheet" href="../public_app/styles/main.css">
    <!-- endbuild -->
  </head>
  <body>
    <!--[if lte IE 8]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <!-- Add your site or application content here -->
<!--     <div class="header"> -->
<!--       <div class="barraSuperior"> -->
<!--         <div class="contenedor"> -->
<!--           <div class=""> -->

            

<!--             <a class="" href="#/">Identity</a> -->
<!--           </div> -->

<!--           <div class=""> -->

<!--             <ul class=""> -->
<!--               <li class=""><a href="#/">Home</a></li> -->
<!--               <li><a ng-href="#!/altaUsuario">altaUsuario</a></li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    
    <div layout="column">
		<md-toolbar>
		
			<md-toolbar-row>
		  
				<a ng-href="#"><img alt="" src="public_app/images/identity.png" style="max-width: 20%;margin-top: 1%;margin-bottom: 1%;margin-left: 1%;"/></a>
				
<!-- 				<md-button type="button" class="botonMenuPrincipal" ng-click="mostrarLogin()">Login</md-button> -->
				
<!-- 				<md-button class="md-primary md-raised" ng-click="showPrompt($event)"  > -->
<!-- 			    	Prompt Dialog -->
<!-- 			    </md-button> -->
				
			</md-toolbar-row>


		</md-toolbar>


		
		<div>
<!-- 			<button class="botonMenuPrincipal" ng-href="#!/altaUsuario">Alta de usuario</button> -->
			
		
			
		</div>
  
     <div class="contenedor">
     
<!--       <form method="post" action="/identity-1.0/login" ng-model="userLogin"> -->
 
<!--  				<md-input-container class="campoTexto"> -->
<!-- 			  		<label>*Correo electrónico</label> -->
<!-- 			  		<input name="user" type="text" ng-model="user.email"> -->
<!-- 				</md-input-container> -->
				
<!-- 				 <md-input-container class="campoTexto"> -->
<!-- 			  		<label>*Contraseña</label> -->
<!-- 			  		<input name="password" type="text" ng-model="user.password"> -->
<!-- 				</md-input-container> -->

    
<!--     <md-button type="submit" class="botonMenuPrincipal" ng-click="login()">Acceder</md-button> -->
 
 
<!-- 	</form> -->




    </div>
  
</div>






    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID -->
     <script>
       !function(A,n,g,u,l,a,r){A.GoogleAnalyticsObject=l,A[l]=A[l]||function(){
       (A[l].q=A[l].q||[]).push(arguments)},A[l].l=+new Date,a=n.createElement(g),
       r=n.getElementsByTagName(g)[0],a.src=u,r.parentNode.insertBefore(a,r)
       }(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

       ga('create', 'UA-XXXXX-X');
       ga('send', 'pageview');
    </script>
    
    <script>

  
    
    </script>

    <!-- build:js(.) scripts/vendor.js -->
    <!-- bower:js -->
    <script src="public_app/bower_components/jquery/dist/jquery.js"></script>
    <script src="public_app/bower_components/angular/angular.js"></script>
    <script src="public_app/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap.js"></script>
    <script src="public_app/bower_components/angular-animate/angular-animate.js"></script>
    <script src="public_app/bower_components/angular-aria/angular-aria.js"></script>
    <script src="public_app/bower_components/angular-cookies/angular-cookies.js"></script>
    <script src="public_app/bower_components/angular-messages/angular-messages.js"></script>
    <script src="public_app/bower_components/angular-resource/angular-resource.js"></script>
    <script src="public_app/bower_components/angular-route/angular-route.js"></script>
    <script src="public_app/bower_components/angular-material/angular-material.js"></script>
    <script src="public_app/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="public_app/bower_components/angular-touch/angular-touch.js"></script>
    <!-- endbower -->
    <!-- endbuild -->

        <!-- build:js({.tmp,app}) scripts/scripts.js -->
        <script src="public_app/scripts/app.js"></script>
        <script src="public_app/scripts/controllers/main.js"></script>
<!--         <script src="scripts/controllers/altaUsuario.js"></script> -->
        <!-- endbuild -->
</body>
</html>
