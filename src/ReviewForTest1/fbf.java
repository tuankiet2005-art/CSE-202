package ReviewForTest1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class fbf {

    static int male;
    static int female;
    static List<Vertex> currentList;

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        int n = rd.nextInt();
        int m = rd.nextInt();

        Vertex[] graph = new Vertex[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new Vertex(i);
            graph[i].gender = rd.next();
        }

        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[u].addAdj(graph[v]);
            graph[v].addAdj(graph[u]);
        }

        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].visited) {

                male = 0;
                female = 0;
                currentList = new ArrayList<>();

                dfs(graph[i]);

                for (Vertex vertex : currentList) {
                    vertex.male = male;
                    vertex.female = female;
                }
            }

        }

        for (int i = 1; i <= n; i++) {
            sb.append(i).append(" ").append(graph[i].male).append(" ").append(graph[i].female).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        if (v.gender.equals("Nam"))
            male++;
        else
            female++;
        v.visited = true;
        currentList.add(v);
        for (Vertex adjVertex : v.adjVertexs) {
            if (!adjVertex.visited) {
                dfs(adjVertex);
            }
        }
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjVertexs = new ArrayList<>();
        String gender;
        int male;
        int female;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdj(Vertex v) {
            this.adjVertexs.add(v);
        }

    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

}
