function parseJwt(token) {
    if (!token) return null;
    return JSON.parse(atob(token.split('.')[1]));
}

function loadDashboard() {

    const token = localStorage.getItem("token");

    fetch("/api/dashboard/customer", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("myReservations").innerText =
            data.totalReservations ?? 0;

        document.getElementById("activeBookings").innerText =
            data.activeBookings ?? 0;

        document.getElementById("totalBills").innerText =
            "₹" + (data.totalBills ?? 0);

        document.getElementById("pendingPayments").innerText =
            "₹" + (data.pendingPayments ?? 0);
    });
}

window.onload = loadDashboard;