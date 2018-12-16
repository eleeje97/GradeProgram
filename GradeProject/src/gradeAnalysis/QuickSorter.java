package gradeAnalysis;

import javax.swing.JRadioButton;

public class QuickSorter{
	
	Object[][] data;
	int sortMethod; //콤보박스에서 선택한 정렬방법을 저장.
	//생성자
	public QuickSorter(Object[][] rowData) {
		this.data = rowData;
	}
	
    public void sort(int sortMethod) {
    	this.sortMethod = sortMethod; //현재 선택한 정렬방법을 저장 = 표의 열index임
    	
    	qsort(0, data.length- 1,sortMethod);
    }
    
    private void qsort(int pre, int post, int sortMethod) {
    	
        int saved_pre = pre;
        int saved_post = post;
        
        Object mid = data[(pre + post) / 2][sortMethod];
       
        do {
			if (sortMethod == 2) { // 선택한 정렬 항목이 성적인 경우에만 내림차순
				// mid보다 작은 data가 나올때까지 pre를 오른쪽으로 이동시킨다.
				while (((Comparable) data[pre][sortMethod]).compareTo(mid) > 0) {
					pre++;
				}
				// mid보다 큰 data가 나올때까지 post를 왼쪽으로 이동시킨다.
				while (((Comparable) mid).compareTo(data[post][sortMethod]) > 0) {
					post--;
				}
			}else { //선택한 정렬 항목이 학번,이름인 경우는 오름차순
				// mid보다 큰 data가 나올때까지 pre를 오른쪽으로 이동시킨다.
				while (((Comparable) data[pre][sortMethod]).compareTo(mid) < 0) {
					pre++;
				}
				// mid보다 작은 data가 나올때까지 post를 왼쪽으로 이동시킨다.
				while (((Comparable) mid).compareTo(data[post][sortMethod]) < 0) {
					post--;
				}
			}
            if (pre <= post) {
                Object[] tmp = data[pre];
                data[pre] = data[post];
                data[post] = tmp;
                pre++;
                post--;
            }
        } while (pre <= post);
        if (saved_pre < post) {
            qsort(saved_pre, post,sortMethod);
        }
        if (pre < saved_post) {
            qsort(pre, saved_post,sortMethod);
        }
    }

}
 
