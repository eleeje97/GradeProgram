package gradeAnalysis;

import javax.swing.JRadioButton;

public class QuickSorter{
	
	Object[][] data;
	int sortMethod; //�޺��ڽ����� ������ ���Ĺ���� ����.
	//������
	public QuickSorter(Object[][] rowData) {
		this.data = rowData;
	}
	
    public void sort(int sortMethod) {
    	this.sortMethod = sortMethod; //���� ������ ���Ĺ���� ���� = ǥ�� ��index��
    	
    	qsort(0, data.length- 1,sortMethod);
    }
    
    private void qsort(int pre, int post, int sortMethod) {
    	
        int saved_pre = pre;
        int saved_post = post;
        
        Object mid = data[(pre + post) / 2][sortMethod];
       
        do {
			if (sortMethod == 2) { // ������ ���� �׸��� ������ ��쿡�� ��������
				// mid���� ���� data�� ���ö����� pre�� ���������� �̵���Ų��.
				while (((Comparable) data[pre][sortMethod]).compareTo(mid) > 0) {
					pre++;
				}
				// mid���� ū data�� ���ö����� post�� �������� �̵���Ų��.
				while (((Comparable) mid).compareTo(data[post][sortMethod]) > 0) {
					post--;
				}
			}else { //������ ���� �׸��� �й�,�̸��� ���� ��������
				// mid���� ū data�� ���ö����� pre�� ���������� �̵���Ų��.
				while (((Comparable) data[pre][sortMethod]).compareTo(mid) < 0) {
					pre++;
				}
				// mid���� ���� data�� ���ö����� post�� �������� �̵���Ų��.
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
 
