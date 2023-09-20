#!/usr/bin/env python3

import os

if __name__ == '__main__':
    lines = open('readme.adoc').readlines()


    def handle_includes(line: str):
        if line.startswith('include::'):
            inc = line.split('include::')[1].split('[')[0]
            inc_file = open(inc).read()
            return inc_file

        return line


    lines = [handle_includes(l).strip() for l in lines]
    content = os.linesep.join(lines)

    with  open('out.adoc','w') as out:
        out.write(content)
