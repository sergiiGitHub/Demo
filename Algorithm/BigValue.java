import java.util.ArrayList;
import java.util.List;


public class BigValue{

    private List< Integer > mValue;

    public BigValue() {
        mValue = new ArrayList<>();
    }

    public BigValue(int value) {
        this();
        
        while( value > 0 ){
        	int insert = value % 10;
            mValue.add( insert );
        	value /= 10;
        }
    }

    void multiply( int value ){
        ArrayList< Integer > buffer = new ArrayList<Integer>(mValue.size());

//        System.out.print( "Before: " );
//        System.out.println( this );
        for ( int i = 0; i < mValue.size(); ++i ){
            int extra = i < buffer.size() ? buffer.get(i) : 0;
            if( i < buffer.size() ){
                buffer.set(i, mValue.get(i) * value + extra );
            } else {
                buffer.add( mValue.get(i) * value + extra);
            }
            
            int element = buffer.get(i) / 10;
            if ( element > 0 ){
                if ( i + 1 < buffer.size() ){
                    buffer.set(i + 1, element );
                } else {
                    buffer.add( element );
                }
            }
            buffer.set(i, buffer.get(i) % 10 );
        }

        mValue = buffer;
//        System.out.print( "After: " );
//        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int i = mValue.size() - 1; i >= 0; --i ){
            sb.append(mValue.get(i));
        }
        return sb.toString();
    }
}