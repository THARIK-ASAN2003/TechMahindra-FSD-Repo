public class PrimeSum {
    static class PrimeSumTask extends Thread {
        int start, end;
        long sum = 0;

        PrimeSumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++)
                if (n % i == 0) return false;
            return true;
        }

        public void run() {
            for (int i = start; i <= end; i++)
                if (isPrime(i))
                    sum += i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int limit = 100;
        int numThreads = 4;
        PrimeSumTask[] primeTasks = new PrimeSumTask[numThreads];
        int range = limit / numThreads;
        int start = 1;

        for (int i = 0; i < numThreads; i++) {
            int end = (i == numThreads - 1) ? limit : start + range - 1;
            primeTasks[i] = new PrimeSumTask(start, end);
            primeTasks[i].start();
            start = end + 1;
        }

        long totalSum = 0;
        for (PrimeSumTask task : primeTasks) {
            task.join();
            totalSum += task.sum;
        }

        System.out.println("Sum of primes up to " + limit + " is " + totalSum);
    }
}