'use strict';

/**
 * @ngdoc function
 * @name identityAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the identityAppApp
 */


var mainCtrlCallBack=function($http, $scope, $mdDialog, $window)
{
	
	if($window.location.href.includes("localhost"))
		$scope.contextRoot="/identity-1.0";
	else
		$scope.contextRoot="";
	
	function loginController($scope, $mdDialog, $http, $window, contextRoot) 
	{	
		$scope.contextRoot=contextRoot;
		
		$scope.sendLogin=function()
		{
			if($scope.user===undefined || $scope.user===null ||
			   $scope.user.email===undefined || $scope.user.email===null ||
			   $scope.user.password===undefined || $scope.user.password===null)
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
			function(answer) 
			{
			 // $scope.status = 'You said the information was "' + answer + '".';
			}
			, 
			function() 
			{
			  //$scope.status = 'You cancelled the dialog.';
			}
		);
	};
	  
	  
	  
	  
	function registerController($scope, $mdDialog, $http, $window, contextRoot) 
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
				$scope.user.isCompany=false;
						  
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
			function(answer) 
			{
				//$scope.status = 'You said the information was "' + answer + '".';
			}
			, 
			function() 
			{
				//$scope.status = 'You cancelled the dialog.';
		    }
		);
  };
	
	
};
		  




angular.module('identity')
  .controller('MainCtrl', ['$http','$scope','$mdDialog','$window',mainCtrlCallBack]);




