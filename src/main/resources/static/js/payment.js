console.log("payment.js loaded");

window.addEventListener("load", () => {

    const params = new URLSearchParams(window.location.search);
    const reservationId = params.get("reservationId");

    if (!reservationId) {
        alert("Reservation missing");
        return;
    }

    document.getElementById("reservationId").value = reservationId;

    loadAmount(reservationId);
});

function calculateNights(checkIn, checkOut) {

    if (!checkIn || !checkOut) {
        console.error("Missing dates:", checkIn, checkOut);
        return 1;
    }

    const inParts = checkIn.split("-");
    const outParts = checkOut.split("-");

    const start = new Date(inParts[0], inParts[1] - 1, inParts[2]);
    const end = new Date(outParts[0], outParts[1] - 1, outParts[2]);

    const diffTime = end - start;

    return diffTime / (1000 * 60 * 60 * 24);
}

function loadAmount(reservationId) {

    fetch(`/api/reservations/${reservationId}`, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(reservation => {

        const roomPrice = reservation.roomPrice || 0;

		const checkIn = reservation.plannedCheckIn;
		    const checkOut = reservation.plannedCheckOut;

        const nights = calculateNights(checkIn, checkOut);

        const totalAmount = roomPrice * nights;

        document.getElementById("amount").value = totalAmount;

        document.getElementById("amountText").innerText =
            `Amount: ₹${roomPrice} × ${nights} night(s) = ₹${totalAmount}`;
    })
    .catch(err => console.error("Error:", err));
}

function payNow() {

    const reservationId = document.getElementById("reservationId").value;

    const paymentData = {
        amount: document.getElementById("amount").value
    };

    fetch(`/api/payments/pay/${reservationId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(paymentData)
    })
    .then(res => {
        if (!res.ok) throw new Error("Payment failed");
        return res.json();
    })
    .then(() => {
        alert("Payment successful → Bill generated");
        window.location.href = "/customer/bills";
    })
    .catch(err => alert("Payment failed"));
}