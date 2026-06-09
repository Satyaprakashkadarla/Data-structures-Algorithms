class Solution {
    canSeatAllPeople(k, seats) {
        let n = seats.length;
        for (let i = 0; i < n && k > 0; i++) {
            if (seats[i] === 0) {
                let left = (i === 0) || (seats[i - 1] === 0);
                let right = (i === n - 1) || (seats[i + 1] === 0);
                if (left && right) {
                    seats[i] = 1;
                    k--;
                    i++; // skip next seat
                }
            }
        }
        return k === 0;
    }
}