import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIUMLMK2 {

    static int numNode = 0;

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        int n = rd.nextInt();

        Vertex[] graph = new Vertex[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[u].adjVertexs.add(graph[v]);
            graph[v].adjVertexs.add(graph[u]);
        }

        for (int i = 0; i < n; i++) {
            graph[i].maxPrice = rd.nextInt();
        }

        int price = rd.nextInt();

        dfs(graph[0], price);

        for (int i = 0; i < n; i++) {
            sb.append(graph[i].product).append(" ");
        }

        //System.out.println(sb);
        System.out.println((int)(1.7));

    }

    static void countNode(Vertex vertex) {
        vertex.visited = true;
        numNode++;
        for (Vertex adjVertex : vertex.adjVertexs) {
            if (!adjVertex.visited) {
                countNode(adjVertex);
            }
        }
    }

    static void dfs(Vertex vertex, int money) {
        vertex.visited = true;
        if (vertex.maxPrice >= money) {
            vertex.product = 1;
        } else
            return;
        for (Vertex adjVertex : vertex.adjVertexs) {
            if (!adjVertex.visited) {
                if (adjVertex.maxPrice >= (int)(money * 1.1)) {
                    dfs(adjVertex, (int) (money * 1.1));
                } else {
                    countNode(adjVertex);
                    vertex.product += numNode;
                    numNode = 0;

                }
            }
        }
    }

    static class Vertex {
        int id;
        List<Vertex> adjVertexs = new ArrayList<>();
        int maxPrice = 0;
        boolean visited;
        int product = 0;

        public Vertex(int id) {
            this.id = id;
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
