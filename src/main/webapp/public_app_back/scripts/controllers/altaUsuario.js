'use strict';

/**
 * @ngdoc function
 * @name identityAppApp.controller:altaUsuarioCtrl
 * @description
 * # altaUsuarioCtrl
 * Controller of the identityAppApp
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

angular.module('identityAppApp')
.controller('altaUsuarioCtrl', ['$http','$scope',altaUsuarioCtrlCallBack]);



