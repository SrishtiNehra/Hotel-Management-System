window.onload = function () {

    let customerId = 1; // later replace with JWT

    fetch("/api/dashboard/customer/" + customerId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("myReservations").innerText = data.totalMyReservations;
        document.getElementById("activeBookings").innerText = data.activeBookings;
        document.getElementById("totalBills").innerText = "₹" + data.totalBills;
        document.getElementById("pendingPayments").innerText = "₹" + data.pendingPayments;

        let billsDiv = document.getElementById("myBills");
        billsDiv.innerHTML = "";

        data.myBills.forEach(b => {
            billsDiv.innerHTML += `
                <div class="p-2 bg-white mb-2 rounded shadow-sm">
                    ₹${b.amount} - ${b.paymentStatus}
                </div>
            `;
        });

    });
};