import java.util.*;

public class Main {

    static final StringBuilder sb = new StringBuilder();
    static final Random random = new Random(32); 
    static final int SIZE = 250_000;
    static final float COEF = 1/100F;

    static class Column {
        String strings[] = new String[5];
    }

    static class LocalCheck {
        Column column;
        int index;
        LocalCheck(Column aColumn, int aIndex) { 
            column = aColumn;
            index = aIndex;
            broke(random.nextInt(column.strings.length - 2) + 1);
        }
        private void broke(int broken) {
            for (int i = 0; i < broken; ++i) {
                column.strings[i] = createString(); 
            }
        }
    }

    public static void main(String[] args) {

        final int saveEach = random.nextInt((int)(SIZE*COEF));
        int index = 0;
        IUsersolution us = new UserSolution();
        IUsersolution usHash = new UserSolutionHash();
        List<LocalCheck> list = new ArrayList<>(SIZE/saveEach);

        while (index < SIZE) {
            Column column = createColumn();
            us.addColumn(column);

            if (index % saveEach == 0) {
                list.add(new LocalCheck(column, index));
            }
            ++index;
        }

        long start = System.currentTimeMillis();
        index = 0;
        for (LocalCheck lc : list) {
            //System.out.println(index++);
            int actual = us.getIndex(lc.column);
            if (actual != lc.index) {
                throw new Error("actual:" + actual + "; expected: " + lc.index);
            }
        }
        System.out.print("Done: List: time: " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        index = 0;
        for (LocalCheck lc : list) {
            //System.out.println(index++);
            int actual = us.getIndex(lc.column);
            if (actual != lc.index) {
                throw new Error("actual:" + actual + "; expected: " + lc.index);
            }
        }
        System.out.print("Done: HashMap: time: " + (System.currentTimeMillis() - start));
    }

    private static Column createColumn() {
        Column column = new Column();
        for (int i = 0; i < column.strings.length; ++i) {
            column.strings[i] = createString();
        }
        return column;
    }

    private static String createString() {
        sb.setLength(0);
        for (int i = 0; i < 10; ++i) {
            sb.append((char)(random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    static class UserSolution implements IUsersolution {

        Column columns[] = new Column[SIZE];
        int count = 0;

        public void addColumn(Column aColumn) {
            columns[count] = aColumn;
            ++count;
        }

        public int getIndex(Column aColumn) {
            for(int i = 0; i < count; ++i) {
                Column cur = columns[i];
                for (int j = 0; j < cur.strings.length; ++j) {
                    if (compare(cur.strings[j], aColumn.strings[j])) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }

    public interface IUsersolution {
        void addColumn(Column aColumn);
        int getIndex(Column column);
    }

    public static boolean compare(String left, String right) {
        for (int i = 0; i < left.length(); ++i) {
            if (left.charAt(i) != right.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    static class UserSolutionHash implements IUsersolution {
                
        HashMap<String, Column> hashs = new HashMap<>(SIZE);
        
        public void addColumn(Column aColumn) {
            for (String str : aColumn.strings) {
                hashs.put(str, aColumn);
            }
        }

        public int getIndex(Column aColumn) {
            for ()
            hashs.get(key)
            for (int j = 0; j < cur.strings.length; ++j) {
                if (compare(cur.strings[j], aColumn.strings[j])) {
                    return i;
                }
            }
            return -1;
        }
    }
}
