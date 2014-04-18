'use strict';

var app = angular.module('hackerpins', [
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngRoute',
        'ui.bootstrap'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/stories/new', {
                templateUrl: 'views/submitstory.html',
                controller: 'SubmitstoryCtrl'
            })
            .when('/stories/upcoming', {
                templateUrl: 'views/upcoming.html',
                controller: 'UpcomingCtrl'
            })
            .when('/stories/:id', {
                templateUrl: 'views/viewstory.html',
                controller: 'ViewstoryCtrl'
            }).when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl'
            }).when('/register', {
                templateUrl: 'views/register.html',
                controller: 'RegistrationCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });

app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
    $rootScope.$on('$routeChangeStart', function (event) {
        var currentUrlLocation = $location.url();
        console.log('Current Location ' + currentUrlLocation);
        if (currentUrlLocation === '/stories/new') {
            if(!AuthService.isLoggedIn()){
                console.log('User not loggedin');
                event.preventDefault();
                $location.path('/login')
            }else{
                console.log('User already loggedin..');
                $location.path('/stories/new');
            }
        }
    })


}]);

app.factory('AuthService', function () {
    var loggedInuser;
    return {
        setUser: function (user) {
            loggedInuser = user;
        },
        isLoggedIn: function () {
            return loggedInuser ? loggedInuser : false;
        }
    }
});

app.filter('moment', function () {
    return function (text) {
        return moment(text, "MMDDYYYY HH mm ss").fromNow();
    }
});
