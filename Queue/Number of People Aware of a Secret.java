class Person {
    int person;
    int day;
    int delay;
    int forgot;

    Person(int day, int delay, int forgot) {
        this.person = person;
        this.day = day;
        this.delay = delay;
        this.forgot = forgot;
    }
}


class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        Queue<Person> queue = new LinkedList<>();
        int result = 1;
        queue.offer(new Person(1, delay, forget));

        while (!queue.isEmpty() && n > 0) {
            LinkedList<Person> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Person currPerson = queue.poll();

                // Current Day
                int currDay = currPerson.day;
                int currPersonDelay = currPerson.delay;
                int currPersonForgot = currPerson.forgot;

                if (currPersonDelay == 0 && currPersonForgot > 0) {
                    result++;

                    // Add New
                    Person newPerson = new Person(currDay, delay, forget);
    
                    newPerson.day += 1;
                    newPerson.delay = Math.max(0, newPerson.delay - 1);
                    newPerson.forgot = Math.max(0, newPerson.forgot - 1);
                    queue.offer(newPerson); // added for furthur process
                }

                if (currPersonForgot == 0) {
                    // Person forgets - remove from count
                    result--;
                } else {
                    // re-insert the currPerson for next day
                    currPerson.day += 1;
                    currPerson.delay = Math.max(0, currPersonDelay - 1);
                    currPerson.forgot = Math.max(0, currPersonForgot - 1);

                    queue.offer(currPerson);
                }

            }
            n--;
        }

        return result;

    }
}