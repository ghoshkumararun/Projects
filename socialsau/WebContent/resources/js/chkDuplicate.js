function chkDuplicate(str, showMsgAt, duplicate) {
	var strCheck = [];
	
	// เก็บข้อความไว้ใน Array
	for (var i = 0; i < str.length; i++) {
		strCheck[i] = str.charAt(i);
	}
	
	var countDuplicate = 1;
	for (var i = 0; i < strCheck.length - 1; i++) {
		// new
		// 012 --> 2 รอบ
		if (strCheck[i] == strCheck[i + 1]) {
			countDuplicate++;
		}else {
			countDuplicate = 1;
		}
	}
	
	if (countDuplicate > duplicate) {
		document.getElementById(showMsgAt).innerHTML = "<label class='control-label msg-error'>ห้ามกรอกซ้ำเกิน " + duplicate + " ตัว</label>";
		return true;
	}
	
	return false;
}

// abababababa
function chkDuplicate2(str, showMsgAt, duplicate) {
	var strCheck = [];
	var length = str.length;
	
	for (var i = 0; i < length; i++) {
		strCheck[i] = str.charAt(i);
	}
	
	var counter = 1;
	for (var i = 0; i < length; i++) {
		for (var j = 0; j < length; j++) {
			if (strCheck[i] == strCheck[j]) {
				counter++;
			}
			if (counter > duplicate) {
				document.getElementById(showMsgAt).innerHTML = "<label class='control-label msg-error'>ห้ามกรอกซ้ำเกิน " + duplicate + " ตัว</label>";
				return false;
			}
		}
		counter = 1;
	}
	return true;
}