function handleSubmitAllClick() {
	const forms = document.querySelectorAll("form");
	const formData1 = new FormData(forms[0]);
	const formData2 = new FormData(forms[1]);

	let reqData = {}; // form 데이터 객체가 아니라 일반 객체로 변환하는 과정 = reqData 객체를 만드는 것

	/**
		formData1 = {
			username : "admin",
			password : "1234"
		}
		entries = [
		["username", "admin"],
		["password", "1234"]
		]
	 */

	for (let entry of formData1.entries()) {
		const [key, value] = entry;
		reqData = {
			...reqData,
			[key]: value
		}
	}

	/**
	formData2 = {
		chk : "chk1",
		chk : "chk2"
		rdo : "rdo1"
	entries = [
	["chk", "chk1"],
	["chk", "chk2"]
	["rdo", "rdo1"]
	]
 */

	let duplicatedKeys = [];// 중복된 키값이 있는지 확인 후 배열로 묶어야 한다
	let keyCount = {}; // 키값들 중에서 중복인 것 찾기

	for (let key of formData2.keys()) {
		if (Object.keys(keyCount).includes(key)) { // 처음에는 빈 배열이 생성됨 : if문 실행 안됨
			keyCount = {
				...keyCount,
				[key]: keyCount[key] + 1
			}
			continue;
		}
		keyCount = {
			...keyCount,
			[key]: 1
		}
	}

	for (let key of Object.keys(keyCount)) {
		if (keyCount[key] > 1) { // 중복이 있으면
			duplicatedKeys = [...duplicatedKeys, key];
		}
	}

	console.log(keyCount);
	console.log(duplicatedKeys);

	for (let entry of formData2.entries()) {
		const [key, value] = entry;
		if (duplicatedKeys.includes(key)) { // 중복된 키가 있으면 / duplicatedKeys = 중복된 키값이 들어가있음
			reqData = {
				...reqData,
				[key]: [...(!reqData[key] ? [] : reqData[key]), value] // 배열 뒤에 추가 - 값이 없으면 [], 값이 있으면 불러와서 새로운 값 추가
			}
			continue;
		}
		reqData = {
			...reqData,
			[key]: value
		}
	}

	console.log(reqData);

	const queryString = new URLSearchParams(reqData).toString();

	fetch(`http://localhost:8080/dvd/form?${queryString}`)
		.then(response => {
			response.text()
				.then(data => {

					const body = document.querySelector("body");
					body.innerHTML += `<h1>${data}</h1>`;
					console.log(data);
				})
		})
}