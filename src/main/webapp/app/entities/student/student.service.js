(function() {
	'use strict'

	angular.module('shopApp').factory('StudentService', StudentService);

	StudentService.$inject = [ '$resource' ];

	function StudentService($resource) {
		var resourceUrl = '/api/student/:id';
		return $resource(resourceUrl, {}, {
			'getAllClass' : {
				url : '/api/class',
				method : 'GET',
				isArray : true
			},
			
			'findByName' : {
				method : 'GET',
				url : '/api/student/search/:key',
				isArray : true
			},
			
			'download': {
				method: 'GET',
				url: '/api/batch_template',
				responseType: 'arraybuffer'
			},
			
			'getFile':{
				method: 'GET',
				url: '/api/getFile'
			},
			
			'query' : {
				method : 'GET',
				isArray : true
			},
			'get' : {
				method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
			},
			'update' : {
				method : 'PUT'
			}
		});
	}
})();