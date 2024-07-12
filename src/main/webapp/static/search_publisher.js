

function handleSearchClickPublisher() {
	const searchInput = document.querySelector(".search-input");
	location.href = `http://localhost:8080/dvd/publisher/search?searchText=${searchInput.value}`
}