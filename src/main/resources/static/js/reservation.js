window.onload = function () {

    const params = new URLSearchParams(window.location.search);

    const roomId = params.get("roomId");

    if (roomId) {
        document.getElementById("roomId").value = roomId;
        document.getElementById("roomId").readOnly = true; // ✅ ADD ONLY
    }
};

function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

function createReservation() {

    const reservation = {
        bookingDate: document.getElementById("bookingDate").value,
        plannedCheckIn: document.getElementById("plannedCheckIn").value,
        plannedCheckOut: document.getElementById("plannedCheckOut").value,
        roomId: document.getElementById("roomId").value,
        customerId: document.getElementById("customerId").value,

        // IMPORTANT: MUST match backend enum
        status: "BOOKED"
    };

    fetch("/api/reservations", {
        method: "POST",
        headers: authHeader(),
        body: JSON.stringify(reservation)
    })
    .then(res => {
        if (!res.ok) throw new Error("Failed");

        alert("Reservation Created!");

        // redirect to bookings page
        window.location.href = "/customer/bookings";
    })
    .catch(err => alert(err.message));
}