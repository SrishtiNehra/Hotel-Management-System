console.log("create-reservation.js loaded");

function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// ================= INIT =================
window.onload = function () {

    const params = new URLSearchParams(window.location.search);

    const roomId = params.get("roomId");
    const customerId = params.get("customerId");

    document.getElementById("roomId").value = roomId;
    document.getElementById("customerId").value = customerId;

    const today = new Date().toISOString().split("T")[0];
    document.getElementById("bookingDate").value = today;

	document.getElementById("plannedCheckIn").min = today;
	document.getElementById("plannedCheckOut").min = today;
};

// ================= CREATE RESERVATION =================
function createReservation() {

	const checkIn = document.getElementById("plannedCheckIn").value;
	const checkOut = document.getElementById("plannedCheckOut").value;

    if (!checkIn || !checkOut) {
        alert("Select dates");
        return;
    }

    if (checkOut <= checkIn) {
        alert("Check-out must be after check-in");
        return;
    }

    const reservation = {
        bookingDate: document.getElementById("bookingDate").value,
        plannedCheckIn: checkIn,
        plannedCheckOut: checkOut,
        roomId: document.getElementById("roomId").value,
        customerId: document.getElementById("customerId").value
    };

    fetch("/api/reservations", {
        method: "POST",
        headers: authHeader(),
        body: JSON.stringify(reservation)
    })
    .then(res => {
        if (!res.ok) throw new Error("Reservation failed");
        return res.json();
    })
    .then(() => {
        alert("Room booked successfully!");
        window.location.href = "/customer/bookings";
    })
    .catch(err => alert(err.message));
}