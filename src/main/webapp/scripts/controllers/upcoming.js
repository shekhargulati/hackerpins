'use strict';

angular.module('hackerpins')
    .controller('UpcomingCtrl', function ($scope, $http, $location, AuthService) {
        $http.get('api/v1/stories/upcoming').success(function (data, status, headers, config) {
            $scope.stories = data;
        }).error(function (data, status, headers, config) {
            alert(status);
        });

        $scope.like = function (story) {
            if (!AuthService.isLoggedIn()) {
                $location.path('/login');
            } else {
                $http.post('api/v1/stories/' + story.id + '/like').success(function (data, status, headers, config) {
                    story.likes = data.likes;
                }).error(function (data, status, headers, config) {
                    alert(status);
                });
            }

        }

        $scope.dislike = function (story) {
            if (!AuthService.isLoggedIn()) {
                $location.path('/login');
            } else {
                $http.post('api/v1/stories/' + story.id + '/dislike').success(function (data, status, headers, config) {
                    story.dislikes = data.dislikes;
                }).error(function (data, status, headers, config) {
                    alert(status);
                });
            }

        }

    });
