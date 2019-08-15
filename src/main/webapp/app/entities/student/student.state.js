(function() {
	'use strict';
	angular.module('shopApp').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider' ];

	function stateConfig($stateProvider) {
		$stateProvider.state('student', {
			parent : 'entity',
			url : 'student',
			data : {},
			views : {
				'content@' : {
					templateUrl : 'app/entities/student/student.html',
					controller : 'StudentController',
					controllerAs : 'vm'
				}
			},
			resolve : {

			}
		}).state('student-add', {
			parent : 'student',
			url : '/add',
			data : {},
			views : {
				'content@' : {
					templateUrl : 'app/entities/student/student-form.html',
					controller : 'StudentFormController',
					controllerAs : 'vm'
				}
			},
			resolve : {

			}
		}).state('student-update', {
			parent : 'student',
			url : 'update/:id',
			data : {},
			views : {
				'content@' : {
					templateUrl : 'app/entities/student/student-form.html',
					controller : 'StudentFormController',
					controllerAs : 'vm'
				}
			},
			resolve : {

			}
		});
	}
})();