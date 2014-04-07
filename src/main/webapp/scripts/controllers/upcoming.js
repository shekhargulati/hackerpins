'use strict';

angular.module('hackerpins')
    .controller('UpcomingCtrl', function ($scope, $http) {
        $http.get('api/v1/stories/upcoming').success(function (data, status, headers, config) {
            $scope.stories = data;
        }).error(function (data, status, headers, config) {
            alert(status);
        });

        $scope.like = function (story) {
            $http.post('api/v1/stories/' + story.id + '/like').success(function (data, status, headers, config) {
                story.likes = data.likes;
            }).error(function (data, status, headers, config) {
                alert(status);
            });
        }

        $scope.dislike = function (story) {
            $http.post('api/v1/stories/' + story.id + '/dislike').success(function (data, status, headers, config) {
                story.dislikes = data.dislikes;
            }).error(function (data, status, headers, config) {
                alert(status);
            });
        }

    });
