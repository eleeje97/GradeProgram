package gradeAnalysis;

public class QuickSorter{
    Comparable[] data;
    public void sort(Comparable[] data) {
        this.data = data;
        qsort(0, data.length - 1);
    }
    private void qsort(int pre, int post) {
        int saved_pre = pre;
        int saved_post = post;
        Comparable mid = data[(pre + post) / 2];
        do {
        	// mid보다 큰 data가 나올때까지 pre를 오른쪽으로 이동시킨다.
            while (data[pre].compareTo(mid) < 0) {
                pre++;
            }
            // mid보다 작은 data가 나올때까지 post를 왼쪽으로 이동시킨다. 
            while (mid.compareTo(data[post]) < 0) {
                post--;
            }
            if (pre <= post) {
                Comparable tmp = data[pre];
                data[pre] = data[post];
                data[post] = tmp;
                pre++;
                post--;
            }
        } while (pre <= post);
        if (saved_pre < post) {
            qsort(saved_pre, post);
        }
        if (pre < saved_post) {
            qsort(pre, saved_post);
        }
    }
}