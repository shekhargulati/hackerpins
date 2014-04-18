'use strict';

angular.module('hackerpins')
    .controller('RegistrationCtrl', function ($scope, $http, $location, AuthService) {


        $scope.register = function () {
            $http.post('api/v1/profiles/register', $scope.profile).success(function (data, status, header, config) {
                toastr.success('You are successfully registered. Please login now.');
                $location.path('/login');
            }).error(function (data, status, header, config) {
                toastr.error('Please submit correct data.');
                console.log(data);
            });
        }


    });
