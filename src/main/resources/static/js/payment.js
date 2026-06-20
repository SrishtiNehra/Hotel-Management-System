window.onload = function () {

    const params = new URLSearchParams(window.location.search);

    const reservationId = params.get("reservationId");

    document.getElementById("reservationId").value = reservationId;

    loadAmount(reservationId); // ✅ ADD
};

function loadAmount(reservationId) {

    fetch(`/api/reservations/${reservationId}`, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(reservation => {

        const amount = reservation.roomPrice; // ✅ DIRECT FROM BACKEND

        document.getElementById("amountText").innerText =
            "Amount: ₹" + Number(amount).toFixed(2);
    })
    .catch(err => {
        console.error(err);
        document.getElementById("amountText").innerText =
            "Amount: ₹0";
    });
}

function payNow() {

    const id = document.getElementById("reservationId").value;

    const paymentData = {
        cardName: document.getElementById("cardName").value,
        cardNumber: document.getElementById("cardNumber").value,
        expiry: document.getElementById("expiry").value,
        cvv: document.getElementById("cvv").value,
        amount: document.getElementById("amount").value
    };

    fetch(`/api/payments/pay/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(paymentData)
    })
    .then(res => res.json())
    .then(() => {

        alert("Payment Successful!");

        // refresh booking list (PAID will show)
        window.location.href = "/customer/bookings";
    });
}