# Nodejs "Hello world" benchmark

## 新版本 nodejs 性能

[node0.6.0 已经发布](http://blog.nodejs.org/2011/11/05/node-v0-6-0/)了，性能提高如何呢？
本文将记录 nodejs 历史更新中所有版本的hello world性能测试。

### 测试环境

    $ uname -a
    Linux xxx 2.6.18-164.el5 #1 SMP Tue Aug 18 15:51:48 EDT 2009 x86_64 x86_64 x86_64 GNU/Linux
    
    $ cat /proc/cpuinfo
    Intel(R) Xeon(R) CPU           E5410  @ 2.33GHz
    
### 测试 helloworld.js

    var http = require('http');
    http.createServer(function (req, res) {
      res.writeHead(200, {'Content-Type': 'text/plain'});
      res.end('Hello World\n');
    }).listen(1337, "127.0.0.1");
    console.log('Server running at http://127.0.0.1:1337/');

### http_load 压测命令 

    $ http_load -p 100 -s 10 http://127.0.0.1:1337/

## 测试结果: fetches/sec

* v0.6.x
<table>
    <tr>
        <th>0.6.5</th><th>0.6.4</th><th>0.6.3</th><th>0.6.2</th><th>0.6.1</th><th>0.6.0</th>
    </tr>
    <tr>
        <td>8060.28</td><td>8111</td><td>8070</td><td>8137.79</td><td>8239.6</td><td>8157.38</td>
    </tr>
</table>
* v0.5.x
<table>
    <tr>
        <th>0.5.10</th><th>0.5.9</th><th>0.5.8</th><th>0.5.7</th><th>0.5.6</th>
        <th>0.5.5</th><th>0.5.4</th><th>0.5.3</th><th>0.5.2</th><th>0.5.1</th><th>0.5.0</th>
    </tr>
    <tr>
        <td>8200</td><td>7258.88</td><td>7070.8</td><td>7097.59</td><td>6995.6</td>
        <td>8073.3</td><td>-</td><td>7931.39</td><td>8126.8</td><td>-</td><td>-</td>
    </tr>
</table>
* v0.4.x
<table>
    <tr>
        <th>0.4.12</th><th>0.4.11</th><th>0.4.10</th><th>0.4.9</th><th>0.4.8</th>
        <th>0.4.7</th><th>0.4.6</th><th>0.4.5</th><th>0.4.4</th>
        <th>0.4.3</th><th>0.4.2</th><th>0.4.1</th><th>0.4.0</th>
    </tr>
    <tr>
        <td>7510</td><td>7557.79</td><td>7600</td><td>7605.7</td><td>6587.9</td>
        <td>7916.1</td><td>7930.88</td><td>7960</td><td>7981.4</td>
        <td>7929.9</td><td>7965.19</td><td>7975.19</td><td>7490</td>
    </tr>
</table>
* v0.3.x
<table>
    <tr>
        <th>0.3.8</th><th>0.3.7</th><th>0.3.6</th><th>0.3.5</th><th>0.3.4</th>
        <th>0.3.3</th><th>0.3.2</th><th>0.3.1</th><th>0.3.0</th>
    </tr>
    <tr>
        <td>7810</td><td>7870.99</td><td>7814.89</td><td>8879.97</td><td>9000</td>
        <td>8999.29</td><td>9200</td><td>9347.3</td><td>-</td>
    </tr>
</table>
* v0.2.x
<table>
    <tr>
        <th>0.2.6</th><th>0.2.5</th><th>0.2.4</th><th>0.2.3</th>
        <th>0.2.2</th><th>0.2.1</th><th>0.2.0</th>
    </tr>
    <tr>
        <td>7524.9</td><td>7480.6</td><td>7488.59</td><td>7500</td>
        <td>7409.99</td><td>7135.9</td><td>7129.99</td>
    </tr>
</table>
* v0.1.x
<table>
    <tr>
        <th>0.1.104</th><th>0.1.103</th><th>0.1.102</th><th>0.1.101</th><th>0.1.100</th>
        <th>0.1.99</th><th>0.1.98</th><th>0.1.97</th><th>0.1.96</th><th>0.1.95</th>
        <th>0.1.94</th><th>0.1.93</th><th>0.1.92</th><th>0.1.91</th><th>0.1.90</th>
        <th>0.1.0</th>
    </tr>
    <tr>
        <td>7640</td><td>7538.38</td><td>7537.49</td><td>7673.9</td><td>7040</td>
        <td>7279.99</td><td>7210.6</td><td>7339.55</td><td>7269.6</td><td>7209.65</td>
        <td>7063.39</td><td>7930.6</td><td>8019.99</td><td>8560.98</td><td>8146.18</td>
        <td>-</td>
    </tr>
</table>

## v0.6.0与v0.4.12的性能对比
在[v0.6.0更新](http://blog.nodejs.org/2011/11/05/node-v0-6-0/) 说明文章中，列出的对比数据

### v0.4.12 (linux) v0.6.0 (linux)

    http_simple.js /bytes/1024  5461 r/s    6263 r/s
    io.js read                  19.75 mB/s  26.63 mB/s
    io.js write                 21.60 mB/s  17.40 mB/s
    startup.js                  74.7 ms     49.6 ms

### v0.4.12 (windows: Cygwin)   v0.6.0 (windows)

    http_simple.js /bytes/1024  3858 r/s    5823 r/s
    io.js read                  12.41 mB/s  26.51 mB/s
    io.js write                 12.61 mB/s  33.58 mB/s
    startup.js                  152.81 ms   52.04 ms

v0.4 和 v0.6之间的更新说明请查看: [API-changes-between-v0.4-and-v0.6](https://github.com/joyent/node/wiki/API-changes-between-v0.4-and-v0.6)

## node0.6.5

    8060.28 fetches/sec, 96723.4 bytes/sec
    msecs/connect: 0.0518643 mean, 0.702 max, 0.021 min
    msecs/first-response: 12.3109 mean, 35.02 max, 4.762 min

## node0.6.4

    8111 fetches/sec, 97332 bytes/sec
    msecs/connect: 0.051521 mean, 1.847 max, 0.021 min
    msecs/first-response: 12.2351 mean, 49.227 max, 1.847 min


## node0.6.3

    8070 fetches/sec, 96840 bytes/sec
    msecs/connect: 0.0517654 mean, 0.502 max, 0.02 min
    msecs/first-response: 12.2967 mean, 51.886 max, 5.151 min

## node0.6.2

    8137.79 fetches/sec, 97653.5 bytes/sec
    msecs/connect: 0.0515829 mean, 0.642 max, 0.021 min
    msecs/first-response: 12.1961 mean, 42.128 max, 3.111 min
    
## node0.6.1

    8239.6 fetches/sec, 98875.2 bytes/sec
    msecs/connect: 0.0530447 mean, 0.439 max, 0.022 min
    msecs/first-response: 12.0422 mean, 32.93 max, 1.846 min
      
## node0.6.0

    8157.38 fetches/sec, 97888.5 bytes/sec
    msecs/connect: 0.0512865 mean, 0.741 max, 0.022 min
    msecs/first-response: 12.1661 mean, 46.023 max, 4.57 min

## node0.5.10

    8200 fetches/sec, 98400 bytes/sec
    msecs/connect: 0.0517871 mean, 0.508 max, 0.024 min
    msecs/first-response: 12.0963 mean, 34.816 max, 4.831 min
      
## node0.5.9

    7258.88 fetches/sec, 87106.6 bytes/sec
    msecs/connect: 0.0521661 mean, 0.667 max, 0.022 min
    msecs/first-response: 13.6817 mean, 49.23 max, 3.31 min

## node0.5.8
      
    7070.8 fetches/sec, 84849.6 bytes/sec
    msecs/connect: 0.0523142 mean, 0.517 max, 0.022 min
    msecs/first-response: 14.0466 mean, 39.545 max, 6.215 min
      
## node0.5.7

    7097.59 fetches/sec, 85171.1 bytes/sec
    msecs/connect: 0.0526457 mean, 0.586 max, 0.024 min
    msecs/first-response: 13.9935 mean, 44.333 max, 5.604 min

## node0.5.6

    6995.6 fetches/sec, 83947.2 bytes/sec
    msecs/connect: 0.0525126 mean, 0.413 max, 0.022 min
    msecs/first-response: 14.1971 mean, 37.55 max, 6.123 min

## node0.5.5

    8073.3 fetches/sec, 96879.6 bytes/sec
    msecs/connect: 0.0509257 mean, 0.41 max, 0.02 min
    msecs/first-response: 12.2924 mean, 44.222 max, 4.323 min

## node0.5.4

出现编译错误:

    /usr/bin/ld: skipping incompatible /usr/lib/libc.a when searching for -lc
    /home/admin/pkgs/node-v0.5.4/build/default/deps/uv/uv.a(eio.o): In function `eio__sync_file_range':
    /home/admin/pkgs/node-v0.5.4/build/default/deps/uv/src/eio/eio.c:1083: undefined reference to `sync_file_range'
    collect2: ld returned 1 exit status
    Waf: Leaving directory `/home/admin/pkgs/node-v0.5.4/build'
    Build failed:  -> task failed (err #1): 
        {task: cxx_link node_main_5.o,node_5.o,node_buffer_5.o,node_javascript_5.o,
        node_extensions_5.o,node_http_parser_5.o,node_constants_5.o,node_file_5.o,
        node_script_5.o,node_os_5.o,node_dtrace_5.o,node_string_5.o,timer_wrap_5.o,
        handle_wrap_5.o,stream_wrap_5.o,tcp_wrap_5.o,pipe_wrap_5.o,cares_wrap_5.o,
        stdio_wrap_5.o,process_wrap_5.o,node_cares_5.o,node_net_5.o,
        node_signal_watcher_5.o,node_stat_watcher_5.o,node_io_watcher_5.o,
        node_stdio_5.o,node_child_process_5.o,node_timer_5.o,platform_linux_5.o,
        node_crypto_5.o,http_parser_3.o -> node}
    make: *** [program] Error 1

## node0.5.3

    7931.39 fetches/sec, 95176.7 bytes/sec
    msecs/connect: 0.0520728 mean, 0.701 max, 0.024 min
    msecs/first-response: 12.5109 mean, 45.553 max, 5.302 min

## node0.5.2

    8126.8 fetches/sec, 97521.6 bytes/sec
    msecs/connect: 0.0513291 mean, 2.476 max, 0.021 min
    msecs/first-response: 12.2076 mean, 38.541 max, 1.328 min

## node0.5.1

编译不通过，暂缺

## node0.5.0

编译不通过，暂缺

## node0.4.12

    7510 fetches/sec, 90120 bytes/sec
    msecs/connect: 0.0540341 mean, 0.631 max, 0.023 min
    msecs/first-response: 13.2113 mean, 47.364 max, 3.807 min
      
## node0.4.11

    7557.79 fetches/sec, 90693.5 bytes/sec
    msecs/connect: 0.0553164 mean, 0.467 max, 0.025 min
    msecs/first-response: 13.13 mean, 44.789 max, 2.899 min

## node0.4.10

    7600 fetches/sec, 91200 bytes/sec
    msecs/connect: 0.0534043 mean, 0.367 max, 0.022 min
    msecs/first-response: 13.0584 mean, 53.123 max, 4.559 min

## node0.4.9

    7605.7 fetches/sec, 91268.4 bytes/sec
    msecs/connect: 0.0539834 mean, 0.379 max, 0.02 min
    msecs/first-response: 13.0469 mean, 46.833 max, 5.299 min

## node0.4.8

    6587.9 fetches/sec, 79054.8 bytes/sec
    msecs/connect: 0.0512022 mean, 0.499 max, 0.021 min
    msecs/first-response: 15.0846 mean, 49.04 max, 4.448 min
      
## node0.4.7

    7916.1 fetches/sec, 94993.2 bytes/sec
    msecs/connect: 0.0516686 mean, 1.629 max, 0.021 min
    msecs/first-response: 12.5375 mean, 43.116 max, 5.695 min

## node0.4.6

    7930.88 fetches/sec, 95170.6 bytes/sec
    msecs/connect: 0.0517224 mean, 0.704 max, 0.022 min
    msecs/first-response: 12.5125 mean, 42.465 max, 5.519 min
      
## node0.4.5

    7960 fetches/sec, 95520 bytes/sec
    msecs/connect: 0.0522368 mean, 0.674 max, 0.021 min
    msecs/first-response: 12.4676 mean, 43.185 max, 5.598 min

## node0.4.4

    7981.4 fetches/sec, 95776.8 bytes/sec
    msecs/connect: 0.0511981 mean, 0.729 max, 0.02 min
    msecs/first-response: 12.4255 mean, 51.829 max, 5.506 min
      
## node0.4.3

    7929.9 fetches/sec, 95158.8 bytes/sec
    msecs/connect: 0.0510949 mean, 0.833 max, 0.023 min
    msecs/first-response: 12.5189 mean, 51.677 max, 5.02 min
      
## node0.4.2

    7965.19 fetches/sec, 95582.3 bytes/sec
    msecs/connect: 0.0526699 mean, 0.864 max, 0.023 min
    msecs/first-response: 12.4563 mean, 42.695 max, 3.897 min
      
## node0.4.1

    7975.19 fetches/sec, 95702.3 bytes/sec
    msecs/connect: 0.0527306 mean, 1.418 max, 0.021 min
    msecs/first-response: 12.4361 mean, 47.871 max, 0.767 min
      
## node0.4.0

    7490 fetches/sec, 89880 bytes/sec
    msecs/connect: 0.051049 mean, 0.691 max, 0.021 min
    msecs/first-response: 13.255 mean, 51.421 max, 5.735 min

## node0.3.8

    7810 fetches/sec, 93720 bytes/sec
    msecs/connect: 0.0514884 mean, 0.581 max, 0.02 min
    msecs/first-response: 12.7136 mean, 43.613 max, 5.42 min

## node0.3.7

    7870.99 fetches/sec, 94451.9 bytes/sec
    msecs/connect: 0.0527374 mean, 0.453 max, 0.022 min
    msecs/first-response: 12.6068 mean, 37.022 max, 5.835 min

## node0.3.6

    7814.89 fetches/sec, 93778.6 bytes/sec
    msecs/connect: 0.0522743 mean, 0.719 max, 0.023 min
    msecs/first-response: 12.6983 mean, 37.436 max, 5.748 min

## node0.3.5

    8879.97 fetches/sec, 106560 bytes/sec
    msecs/connect: 0.0571404 mean, 0.753 max, 0.022 min
    msecs/first-response: 11.1496 mean, 46.028 max, 4.842 min

## node0.3.4

    9000 fetches/sec, 108000 bytes/sec
    msecs/connect: 0.0566353 mean, 0.633 max, 0.023 min
    msecs/first-response: 11.0017 mean, 46.633 max, 5.126 min

## node0.3.3

    8999.29 fetches/sec, 107992 bytes/sec
    msecs/connect: 0.0569282 mean, 0.583 max, 0.022 min
    msecs/first-response: 11.0035 mean, 43.218 max, 5.112 min

## node0.3.2

    9200 fetches/sec, 110400 bytes/sec
    msecs/connect: 0.0582272 mean, 0.709 max, 0.023 min
    msecs/first-response: 10.7557 mean, 32.685 max, 5.059 min

## node0.3.1

    9347.3 fetches/sec, 112168 bytes/sec
    msecs/connect: 0.0564677 mean, 0.974 max, 0.022 min
    msecs/first-response: 10.592 mean, 38.921 max, 4.77 min

## node0.3.0

编译不通过

## node0.2.6

    7524.9 fetches/sec, 90298.7 bytes/sec
    msecs/connect: 0.0581787 mean, 0.649 max, 0.02 min
    msecs/first-response: 13.1609 mean, 105.413 max, 5.191 min

## node0.2.5

    7480.6 fetches/sec, 89767.1 bytes/sec
    msecs/connect: 0.0589546 mean, 0.693 max, 0.023 min
    msecs/first-response: 13.2442 mean, 94.122 max, 5.744 min

## node0.2.4

    7488.59 fetches/sec, 89863.1 bytes/sec
    msecs/connect: 0.0595586 mean, 0.428 max, 0.023 min
    msecs/first-response: 13.2342 mean, 95.229 max, 5.88 min

## node0.2.3

    7500 fetches/sec, 89999.9 bytes/sec
    msecs/connect: 0.0591254 mean, 0.57 max, 0.024 min
    msecs/first-response: 13.1974 mean, 94.721 max, 5.728 min

## node0.2.2

    7409.99 fetches/sec, 88919.9 bytes/sec
    msecs/connect: 0.0597531 mean, 0.558 max, 0.021 min
    msecs/first-response: 13.3618 mean, 102.09 max, 5.846 min

## node0.2.1

    7135.9 fetches/sec, 85630.8 bytes/sec
    msecs/connect: 0.0565066 mean, 0.59 max, 0.023 min
    msecs/first-response: 13.8999 mean, 100.855 max, 5.703 min

## node0.2.0

    7129.99 fetches/sec, 85559.9 bytes/sec
    msecs/connect: 0.0565618 mean, 0.356 max, 0.02 min
    msecs/first-response: 13.915 mean, 100.048 max, 4.344 min

## node0.1.104

    7640 fetches/sec, 91679.9 bytes/sec
    msecs/connect: 0.0577092 mean, 0.564 max, 0.024 min
    msecs/first-response: 12.9695 mean, 90.161 max, 3.711 min

## node0.1.103

    7538.38 fetches/sec, 90460.6 bytes/sec
    msecs/connect: 0.0577052 mean, 1.091 max, 0.021 min
    msecs/first-response: 13.1506 mean, 92.522 max, 5.378 min

## node0.1.102

    7537.49 fetches/sec, 90449.9 bytes/sec
    msecs/connect: 0.0583015 mean, 0.396 max, 0.024 min
    msecs/first-response: 13.1514 mean, 103.188 max, 5.577 min

## node0.1.101

    7673.9 fetches/sec, 92086.8 bytes/sec
    msecs/connect: 0.0588679 mean, 0.767 max, 0.021 min
    msecs/first-response: 12.9082 mean, 99.924 max, 4.776 min

## node0.1.100

    7040 fetches/sec, 84480 bytes/sec
    msecs/connect: 0.0557378 mean, 0.592 max, 0.022 min
    msecs/first-response: 14.0982 mean, 107.368 max, 6.009 min

## node0.1.99

    7279.99 fetches/sec, 87359.9 bytes/sec
    msecs/connect: 0.0555776 mean, 0.569 max, 0.022 min
    msecs/first-response: 13.6292 mean, 93.241 max, 5.983 min

## node0.1.98

    7210.6 fetches/sec, 86527.2 bytes/sec
    msecs/connect: 0.0552844 mean, 0.379 max, 0.02 min
    msecs/first-response: 13.7576 mean, 94.502 max, 5.685 min

## node0.1.97

    7339.55 fetches/sec, 88074.6 bytes/sec
    msecs/connect: 0.057371 mean, 0.802 max, 0.022 min
    msecs/first-response: 13.5052 mean, 89.268 max, 5.326 min

## node0.1.96

    7269.6 fetches/sec, 87235.2 bytes/sec
    msecs/connect: 0.0566584 mean, 0.573 max, 0.021 min
    msecs/first-response: 13.6483 mean, 60.543 max, 4.296 min

## node0.1.95

    7209.65 fetches/sec, 86515.9 bytes/sec
    msecs/connect: 0.0565087 mean, 0.529 max, 0.024 min
    msecs/first-response: 13.7617 mean, 60.438 max, 4.418 min

## node0.1.94

    7063.39 fetches/sec, 84760.6 bytes/sec
    msecs/connect: 0.0547156 mean, 0.66 max, 0.022 min
    msecs/first-response: 14.0522 mean, 130.751 max, 4.987 min

## node0.1.93

    7930.6 fetches/sec, 95167.2 bytes/sec
    msecs/connect: 0.0587932 mean, 0.61 max, 0.023 min
    msecs/first-response: 12.4891 mean, 77.833 max, 3.689 min

## node0.1.92

    8019.99 fetches/sec, 96239.9 bytes/sec
    msecs/connect: 0.058158 mean, 0.718 max, 0.021 min
    msecs/first-response: 12.354 mean, 79.033 max, 3.423 min

## node0.1.91

    8560.98 fetches/sec, 102732 bytes/sec
    msecs/connect: 0.0596777 mean, 0.471 max, 0.022 min
    msecs/first-response: 11.522 mean, 71.727 max, 3.235 min

## node0.1.90

    8146.18 fetches/sec, 97754.2 bytes/sec
    msecs/connect: 0.0595273 mean, 0.826 max, 0.021 min
    msecs/first-response: 12.1531 mean, 223.064 max, 3.189 min

## node0.1.0

helloworld.js跑不起来了，出现一下异常：

    hello.js:8: TypeError: Object #<an Object> has no method 'createServer'
    http.createServer(function (req, res) {
         ^
        
## 有爱
^_^ 希望本文对你有用