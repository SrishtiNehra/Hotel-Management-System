window.onload = function () {

    let customerId = 1; // ideally from JWT decode later

    fetch("/api/dashboard/customer/" + customerId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("myReservations").innerText =
            data.totalMyReservations;

        let billsDiv = document.getElementById("myBills");

        data.myBills.forEach(b => {
            billsDiv.innerHTML += `
                <p>₹${b.amount} - ${b.paymentStatus}</p>
            `;
        });

    });
};