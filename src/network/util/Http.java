package network.util;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class Http {

    public static String POSTRequest(String host, int port, String uri, String post_data) throws IOException {
        String http_response;
        try (Socket http_socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
            http_socket.connect(address);
            try (BufferedReader http_buffered_input = new BufferedReader(new InputStreamReader(http_socket.getInputStream())); DataOutputStream http_output = new DataOutputStream(http_socket.getOutputStream())) {
                String post_request = "POST /" + uri + " HTTP/1.1\r\nHost: " + host + "\r\nContent-Length: " + post_data.length() + "\r\nContent-Type: application/x-www-form-urlencoded\r\n\r\n" + post_data;
                http_output.writeBytes(post_request);
                http_output.flush();
                String http_header_line;
                int http_content_length = 108;
                while (true) {
                    http_header_line = http_buffered_input.readLine();
                    if (http_header_line.contains("Content-Length: ")) {
                        http_content_length = Integer.parseInt(http_header_line.substring(http_header_line.indexOf(": ") + 2));
                    }
                    if (http_header_line.equals("")) {
                        break;
                    }
                }
                char[] b = new char[http_content_length];
                http_buffered_input.read(b, 0, http_content_length);
                http_response = new String(b);
            }
        }
        return http_response;
    }

    public static String POSTRequest(String host, int port, String uri, String get_data, String post_data) throws IOException {
        Socket http_socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
        http_socket.connect(address);
        BufferedReader http_buffered_input = new BufferedReader(new InputStreamReader(http_socket.getInputStream()));
        DataOutputStream http_output = new DataOutputStream(http_socket.getOutputStream());
        String post_request = "POST /" + uri + "?" + get_data + " HTTP/1.1\r\nHost: " + host + "\r\nContent-Length: " + post_data.length() + "\r\n\r\n" + post_data;
        http_output.writeBytes(post_request);
        http_output.flush();
        String http_header_line;
        int http_content_length = 0;
        while (true) {
            http_header_line = http_buffered_input.readLine();
            if (http_header_line.contains("Content-Length: ")) {
                http_content_length = Integer.parseInt(http_header_line.substring(http_header_line.indexOf(": ") + 2));
            }
            if (http_header_line.equals("")) {
                break;
            }
        }
        char[] b = new char[http_content_length];
        http_buffered_input.read(b, 0, http_content_length);
        String http_response = new String(b);
        http_output.close();
        http_buffered_input.close();
        http_socket.close();
        return http_response;
    }

    public static String GETRequest(String host, int port, String uri, String get_data) throws IOException {
        Socket http_socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
        http_socket.connect(address);
        BufferedReader http_buffered_input = new BufferedReader(new InputStreamReader(http_socket.getInputStream()));
        DataOutputStream http_output = new DataOutputStream(http_socket.getOutputStream());
        String get_request = "GET /" + uri + "?" + get_data + " HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
        http_output.writeBytes(get_request);
        http_output.flush();
        String http_header_line;
        int http_content_length = 0;
        while (true) {
            http_header_line = http_buffered_input.readLine();
            if (http_header_line.contains("Content-Length: ")) {
                http_content_length = Integer.parseInt(http_header_line.substring(http_header_line.indexOf(": ") + 2));
            }
            if (http_header_line.equals("")) {
                break;
            }
        }
        char[] b = new char[http_content_length];
        http_buffered_input.read(b, 0, http_content_length);
        String http_response = new String(b);
        http_output.close();
        http_buffered_input.close();
        http_socket.close();
        return http_response;
    }

    public static String GoogleRequest(String host, int port, String uri, String get_data) throws IOException {
        Socket http_socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
        http_socket.connect(address);
        InputStream in = http_socket.getInputStream();
        String get_request = "GET /" + uri + "?" + get_data + " HTTP/1.1\r\nHost: " + host + "\r\n"
                + "Host: translate.google.com\r\n"
                + "Connection: keep-alive\r\n"
                + "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1\r\n"
                + "Accept: */*\r\n"
                + "X-Chrome-Variations: COC1yQEIi7bJAQictskBCKO2yQEIqLbJAQiptskBCLqDygE=\r\n"
                + "Referer: http://translate.google.com/\r\n"
                + "Accept-Encoding: gzip,deflate,sdch\r\n"
                + "Accept-Language: de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4\r\n"
                + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n\r\n";
                
        http_socket.getOutputStream().write(get_request.getBytes());
        String http_header_line;
        int http_content_length = 0;
        while (true) {
            http_header_line = "";
            while(!http_header_line.contains("\r\n")) {
                http_header_line += (char) in.read();            
            }
            if (http_header_line.contains("Content-Length: ")) {
                http_content_length = Integer.parseInt(http_header_line.substring(http_header_line.indexOf(": ") + 2, http_header_line.length() - 2));
            }
            if (http_header_line.equals("\r\n")) {
                break;
            }
        }
        byte[] b = new byte[http_content_length];
        in.read(b);
        GZIPInputStream z_in = new GZIPInputStream(new ByteArrayInputStream(b));
        byte[] c = new byte[4096];
        int d_len = z_in.read(c);
        String http_response = new String(c, 0, d_len);
        http_socket.close();
        return http_response;
    }

    public static String GETRequest(String host, int port, String uri) throws IOException {
        Socket http_socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
        http_socket.connect(address);
        BufferedReader http_buffered_input = new BufferedReader(new InputStreamReader(http_socket.getInputStream()));
        DataOutputStream http_output = new DataOutputStream(http_socket.getOutputStream());
        String get_request = "GET /" + uri + " HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
        http_output.writeBytes(get_request);
        http_output.flush();
        String http_header_line;
        int http_content_length = 0;
        while (true) {
            http_header_line = http_buffered_input.readLine();
            if (http_header_line.contains("Content-Length: ")) {
                http_content_length = Integer.parseInt(http_header_line.substring(http_header_line.indexOf(": ") + 2));
            }
            if (http_header_line.equals("")) {
                break;
            }
        }
        char[] b = new char[http_content_length];
        http_buffered_input.read(b, 0, http_content_length);
        String http_response = new String(b);
        http_output.close();
        http_buffered_input.close();
        http_socket.close();
        return http_response;
    }
}
