function search1() {
    const list = document.querySelectorAll(".tacgia");
    const keyword = document.querySelector("#searchinput").value;
    Object.values(list).map((item) => {
      if (item.innerText.includes(keyword)) {
        item.style.display = "block";
      } else {
        item.style.display = "none";
      }
    });
  }

