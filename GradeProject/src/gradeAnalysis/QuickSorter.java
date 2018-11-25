package gradeAnalysis;

public class QuickSorter {
    
    public void quick(int Array[], int l, int r)
    {
        int left = l;
        int right = r;
        
        //�Ǻ����� ���Ѵ�.
        int pivot = (left+right)/2;
        
        do
        {
            //�߰� ���� �� ũ�ٸ� left ��ĭ �̵��Ѵ�.
            while(Array[left]<Array[pivot]) left++;
            //�߰� ���� �� �۴ٸ� right ��ĭ �̵��Ѵ�.
            while(Array[pivot]<Array[right]) right--;
            
            //right ���� left������ ũ�ų� ���ٸ� temp�� ����
            if(left <= right)
            {
                int temp = Array[left];
                Array[left] = Array[right];
                Array[right] = temp;
                
                
                left++;
                right--;
            }
        }while(left <= right); //���ų� ������ �� ũ�� while���� ������.
        
        //pivot���� ���� �κа� ū �κ��� ��� ȣ�� �Ͽ� ���ؼ� ����
        if(l < right) quick(Array, l, right);
        if(left < r ) quick(Array, left, r);
        
    }
    

}
