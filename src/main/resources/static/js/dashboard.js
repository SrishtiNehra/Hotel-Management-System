window.onload = function () {

    //const token = localStorage.getItem("token");

    fetch("/api/dashboard/admin", {
		method: "GET",
		   headers: {
		       "Authorization": "Bearer " + localStorage.getItem("token")
		   }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("customersCount").innerText = data.totalCustomers;
        document.getElementById("roomsCount").innerText = data.totalRooms;
        document.getElementById("reservationsCount").innerText = data.totalReservations;
        document.getElementById("billsCount").innerText = data.totalBills;
    });
};