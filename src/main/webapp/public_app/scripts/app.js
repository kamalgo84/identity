'use strict';

/**
 * @ngdoc overview
 * @name identityAppApp
 * @description
 * # identityAppApp
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
