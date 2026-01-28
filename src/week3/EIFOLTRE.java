package week3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

public class EIFOLTRE {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);

        HashMap<String, Vertex> graph = new HashMap<>();

        int n = rd.nextInt();

        for (int i = 0; i < n - 1; i++) {
            String u = rd.next();
            String v = rd.next();

            if(graph.get(u) == null){
                Vertex uu = new Vertex(u);
                graph.put(u, uu);
            }

            if (graph.get(v) == null) {
                Vertex vv = new Vertex(v);
                graph.put(v, vv);
            }

            graph.get(u).adjecentVertex.add(graph.get(v));
            graph.get(v).adjecentVertex.add(graph.get(u));
        }

        String root = rd.next();

        dfs(graph.get(root), 0);
        System.out.println(sb);
    }

    static class Vertex{
        String name;
        boolean visited;
        List<Vertex> adjecentVertex = new ArrayList<>();

        public Vertex(String name){
            this.name = name;
        }
    }

    static void dfs(Vertex vertex, int depth){
        vertex.visited = true;
        sb.append("-");
        for (int i = 0; i < depth; i++) {
            sb.append("---");
        }
        sb.append(vertex.name).append("\n");
        vertex.adjecentVertex.sort((s1,s2)->s1.name.compareToIgnoreCase(s2.name));
        for (Vertex adjVertex : vertex.adjecentVertex) {
            if(!adjVertex.visited){
                dfs(adjVertex, depth + 1);
            }
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
