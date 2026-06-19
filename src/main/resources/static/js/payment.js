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
    .then(data => {

        // simple bill logic (room-based placeholder)
        document.querySelector("h4").innerText =
            "Amount: ₹2000 (Room " + data.roomId + ")";
    });
}

function payNow() {

    const id = document.getElementById("reservationId").value;

    fetch(`/api/payments/pay/${id}`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(() => {

        alert("Payment Successful!");

        window.location.href = "/customer/bookings";
    });
}