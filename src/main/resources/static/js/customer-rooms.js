console.log("customer-rooms.js loaded");

window.onload = function () {
    loadRooms();
};

function loadRooms() {

    fetch("/api/rooms", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        console.log("ROOMS FROM API:", data);

        let html = "";

        // ✅ ONLY AVAILABLE ROOMS
        const availableRooms = data.filter(r => r.status === "AVAILABLE");

        availableRooms.forEach(room => {

            html += `
                <div class="col-md-4 mb-3">

                    <div class="card-room">

                        <h4>Room ${room.roomNumber}</h4>
                        <p>Type: ${room.roomType}</p>
                        <p>Price: ₹${room.price}</p>
                        <p>Status: ${room.status}</p>

                        <button class="btn btn-primary w-100"
                            onclick="bookRoom(${room.roomId})">
                            Book Now
                        </button>

                    </div>

                </div>
            `;
        });

        document.getElementById("roomGrid").innerHTML = html;
    })
    .catch(err => console.log("ERROR loading rooms:", err));
}

function bookRoom(roomId) {

    const customerId = localStorage.getItem("customerId");

    if (!customerId) {
        alert("Customer not logged in properly");
        return;
    }

    window.location.href =
        `/customer/create-reservation?roomId=${roomId}&customerId=${customerId}`;
}