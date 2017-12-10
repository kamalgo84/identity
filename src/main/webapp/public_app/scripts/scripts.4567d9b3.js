'use strict';

/**
 * @ngdoc overview
 * @name identity
 * @description
 * # identity
 *
 * Main module of the application.
 */
angular
  .module('identity', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngMaterial',
    'ngTouch'
  ])
//  .config(function ($routeProvider) {
//    $routeProvider
//      .when('/', {
//        templateUrl: 'views/main.html',
//        controller: 'MainCtrl',
//        controllerAs: 'main'
//      })
//      .when('/about', {
//        templateUrl: 'views/about.html',
//        controller: 'AboutCtrl',
//        controllerAs: 'about'
//      })
//      .when('/altaUsuario', {
//          templateUrl: 'views/altaUsuario.html',
//          controller: 'altaUsuarioCtrl',
//          controllerAs: 'about'
//        })
//      .otherwise({
//        redirectTo: '/'
//      });
//  })
  ;

'use strict';

/**
 * @ngdoc function
 * @name identity.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the identity
 */


var mainCtrlCallBack=function($http, $scope, $mdDialog, $window)
{
	
	$scope.contextRoot="";
	
	if($window.location.host==="localhost:9000")
	{
		$scope.contextRoot+="http://localhost:8085";
	}
	
	var loginController=function($scope, $mdDialog, $http, $window, contextRoot) 
	{	
		$scope.contextRoot=contextRoot;
		
		$scope.sendLogin=function()
		{
			if($scope.user===undefined || $scope.user===null ||
			   $scope.user.email===undefined || $scope.user.email===null || $scope.user.email==="" ||
			   $scope.user.password===undefined || $scope.user.password===null || $scope.user.password==="")
			{
				//Parametros no informados
				console.log("senlogin...");
				
				//TODO
				//Informar a la vista del error (Modificar el estilo de las cajas)
				
				$scope.mensaje="Campos no informados";
				
				return;
				
				
			}
			console.log("senlogin...");
						  
//				var user_json = {user: $scope.user.email,password: $scope.user.password};
			var user_json = "user="+ $scope.user.email+"&password="+$scope.user.password;
			
			var config = { headers : {'Content-Type': 'application/x-www-form-urlencoded'}};//application/json 
			
			
			$http.post($scope.contextRoot+"/login", user_json, config)
			.then(function (data, status, headers, config) {
				 
				console.log("Respuesta: "+data);
				
				if(data.data.success===true)
				{
					$window.location.href = $scope.contextRoot+'/identity_app/home';
				}
				else
				{
					$scope.mensaje="Correo o password Incorrecto";
				}
			})
			.catch(function (data, status, header, config) {
				 
				console.log("Respuesta ERROR: "+data);
			})
			.finally(function (data, status, header, config) {
				 
				console.log("Respuesta Finally: "+data);
			});
		  
		};
	  
		$scope.hide = function() {
		  $mdDialog.hide();
		};
		
		$scope.cancel = function() {
		  $mdDialog.cancel();
		};
		
		$scope.answer = function(answer) {
		  $mdDialog.hide(answer);
		};
			    
	};	 
	 
	  
	$scope.showLogin = function(ev) 
	{
		$mdDialog.show
		({
			controller: loginController,
		    templateUrl: 'loginDialog.html',
		    parent: angular.element(document.body),
		    targetEvent: ev,
		    locals: {contextRoot: $scope.contextRoot},
		    clickOutsideToClose:true,
		    fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		})
		.then
		(
//			function(answer) 
//			{
//			 // $scope.status = 'You said the information was "' + answer + '".';
//			}
//			, 
//			function() 
//			{
//			  //$scope.status = 'You cancelled the dialog.';
//			}
		);
	};
	  
	  
	
	
	var registerController=function($scope, $mdDialog, $http, $window, contextRoot) 
	{	
		$scope.contextRoot=contextRoot;
		
		$scope.sendAddUser=function()
		{
			if($scope.user===undefined || $scope.user===null ||
			   $scope.user.email===undefined || $scope.user.email===null ||
			   $scope.user.password===undefined || $scope.user.password===null||
			   $scope.user.password2===undefined || $scope.user.password2===null||
			   $scope.user.password!==$scope.user.password2)
			{
				//Parametros no informados
				console.log("sendAddUser...");
				
				//TODO
				//Informar a la vista del error (Modificar el estilo de las cajas)
				
				$scope.mensaje="Campos no informados";
				
				return;
				
				
			}
			console.log("sendAddUser...");
			
			if($scope.user.isCompany===undefined)
			{
				$scope.user.isCompany=false;
			}
						  
//				var user_json = {user: $scope.user.email,password: $scope.user.password};
			var user_json = "isCompany="+$scope.user.isCompany+"&user="+ $scope.user.email+"&password="+$scope.user.password;
			
			var config = { headers : {'Content-Type': 'application/x-www-form-urlencoded'}};//application/json 
			
			
			$http.post($scope.contextRoot+"/addUser", user_json, config)
			.then(function (data, status, headers, config) {
				 
				console.log("Respuesta: "+data);
				
				if(data.data.success===true)
				{
					$window.location.href = $scope.contextRoot+'/identity_app/home';
				}
				else
				{
					$scope.mensaje="Correo o password Incorrecto";
				}
			})
			.catch(function (data, status, header, config) {
				 
				console.log("Respuesta ERROR: "+data);
			})
			.finally(function (data, status, header, config) {
				 
				console.log("Respuesta Finally: "+data);
			});
		  
		};
	  
		$scope.hide = function() {
		  $mdDialog.hide();
		};
		
		$scope.cancel = function() {
		  $mdDialog.cancel();
		};
		
		$scope.answer = function(answer) {
		  $mdDialog.hide(answer);
		};
			    
	};	 
	  
	$scope.showRegister = function(ev) 
	{
		$mdDialog.show
		({
			controller: registerController,
			templateUrl: 'registerDialog.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			locals: {contextRoot: $scope.contextRoot},
			clickOutsideToClose:true,
			fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		})
		.then
		(
//			function(answer) 
//			{
//				//$scope.status = 'You said the information was "' + answer + '".';
//			}
//			, 
//			function() 
//			{
//				//$scope.status = 'You cancelled the dialog.';
//		    }
		);
  };
	
	
};
		  




angular.module('identity')
  .controller('MainCtrl', ['$http','$scope','$mdDialog','$window',mainCtrlCallBack]);





'use strict';

/**
 * @ngdoc function
 * @name identity.controller:altaUsuarioCtrl
 * @description
 * # altaUsuarioCtrl
 * Controller of the identity
 */

var altaUsuarioCtrlCallBack=function($http, $scope)
{
	$scope.altaUsuario= function()
	{
		
		 var user_json = {
		          
				 codigo_usuario: $scope.user.email,
				 nombre_1: $scope.user.nombre_1,
				 nombre_2: $scope.user.nombre_2,
				 apellidos_1: $scope.user.apellidos_1,
				 apellidos_2: $scope.user.apellidos_2,
				 fecha_nacimiento: $scope.user.fecha_nacimiento,
				 edad: $scope.user.edad,
				 sexo: $scope.user.sexo,
				 lugar_de_nacimiento: $scope.user.lugar_de_nacimiento,
				 provincia_nacimiento: $scope.user.provincia_nacimiento,
				 direccion_habitual: $scope.user.direccion_habitual,
				 tipo_de_via: $scope.user.tipo_de_via,
				 nombre_de_la_calle: $scope.user.nombre_de_la_calle,
				 numero: $scope.user.numero,
				 bloque: $scope.user.bloque,
				 portal: $scope.user.portal,
				 planta: $scope.user.planta,
				 planta_letra: $scope.user.planta_letra,
				 codigo_postal: $scope.user.codigo_postal,
				 municipio: $scope.user.municipio,
				 provincia: $scope.user.provincia,
				 pais_nombre: $scope.user.pais_nombre,
				 codigo_pais: "ES"
			        
		 };
		
		console.log("Alta de usuario");
		
		 var config = {
	                headers : {
	                    'Content-Type': 'application/json'
	                }
	            }

		
		 $http.post("http://identity-kwebgl.rhcloud.com/services/identityServices/addUser", user_json, config)
         .success(function (data, status, headers, config) {
        	 
        	 console.log("Respuesta: "+data);
         })
         .error(function (data, status, header, config) {
        	 
        	 console.log("Respuesta ERROR: "+data);
         });
		 
		 
	};
	
	 
	
	
};

angular.module('identity')
.controller('altaUsuarioCtrl', ['$http','$scope',altaUsuarioCtrlCallBack]);



