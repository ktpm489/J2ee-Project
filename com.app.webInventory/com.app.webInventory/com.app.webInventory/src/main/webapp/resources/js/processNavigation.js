$(document).ready(function()
{
			$("#listStock").on('click', function()
			{

				listStock();
			});
			$("#listStaff").on('click', function()
			{

				listStaff();
			});
			$("#listProvider").on('click', function()
			{

				listProvider();
			});
			$("#listCustomer").on('click', function()
			{

				listCustomer();
			});$("#listTransfer").on('click', function()
			{

				listTransfer();
			});
			$("#listInward").on('click', function()
			{

				listInward();
			});
			$("#listOutward").on('click', function()
			{

				listOutward();
			});
			$("#listCheckStock").on('click', function()
			{

				listCheckStock();
			});
			$("#addStock").on('click', function()
			{

				addStock();
			});
			$("#addInward").on('click', function()
			{

				addInward();
			});
			$("#addOutward").on('click', function()
			{

				addOutward();
			});
			$("#addTransfer").on('click', function()
			{

				addTransfer();
			});
			$("#addCheckStock").on('click', function()
			{

				addCheckStock();
			});
			$("#addProduct").on('click', function()
			{

				addProduct();
			});
			$("#addStaff").on('click', function()
			{

				addStaff();
			});
			$("#addProvider").on('click', function()
			{

				addProvider();
			});
			$("#addCustomer").on('click', function()
			{

				addCustomer();
			});
			$("#logout").on('click', function()
			{

				logout();
			});
});
	   
	function listStock()
	{
		 window.location.href = '/inventory/listStockPage';
	};
	function listStaff()
	{
		 window.location.href = '/inventory/listStaffPage';
	};
	function listProvider()
	{
		 window.location.href = '/inventory/listProviderPage';
	};
	function listCustomer()
	{
		 window.location.href = '/inventory/listCustomerPage';
	};
	function listTransfer()
	{
		 window.location.href = '/inventory/listStockTransferPage';
	};function listInward()
	{
		 window.location.href = '/inventory/listStockInwardPage';
	};function listOutward()
	{
		 window.location.href = '/inventory/listStockOutwardPage';
	};
	function listCheckStock()
	{
		 window.location.href = '/inventory/listStockCheckPage';
	};
	function addStock()
	{
		 window.location.href = '/inventory/addstockPage';
	};
	function addInward()
	{
		 window.location.href = '/inventory/pageInsertStockInward';
	};
	function addOutward()
	{
		 window.location.href = '/inventory/addstockoutwardPage';
	};
	function addTransfer()
	{
		 window.location.href = '/inventory/addstockTransferPage';
	};
	function addCheckStock()
	{
		 window.location.href = '/inventory/addNewStockCheck';
	};
	function addProduct()
	{
		 window.location.href = '/inventory/addproductpage';
	};
	function addStaff()
	{
		 window.location.href = '/inventory/addStaff';
	};
	function addProvider()
	{
		 window.location.href = '/inventory/addproviderPage';
	};
	function addCustomer()
	{
		 window.location.href = '/inventory/addcustomerPage';
	};
	function logout()
	{
		 window.location.href = '/inventory/loginPage';
	};

	

	


	
	