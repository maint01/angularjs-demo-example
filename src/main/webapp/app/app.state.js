(function(){
	'use strict'
	
	angular
		.module('shopApp')
		.config(stateConfig);
	
	stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
	
	function stateConfig($stateProvider, $urlRouterProvider){
		$stateProvider
			.state('entity', {
// abstract: true
				url: '/',
				views : {
					'content@' : {
						templateUrl : 'app/entities/student/student.html',
						controller : 'StudentController',
						controllerAs : 'vm'
					}
				}
			});
		$urlRouterProvider.otherwise('/');
	}
})();