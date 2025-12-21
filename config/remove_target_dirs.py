#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import shutil

def remove_target_dirs(root_path):
    """
    遍历指定目录，删除所有名为'target'的文件夹
    """
    target_dirs = []

    # 遍历目录树
    for dirpath, dirnames, filenames in os.walk(root_path):
        # 检查当前目录下是否有名为'target'的子目录
        if 'target' in dirnames:
            target_path = os.path.join(dirpath, 'target')
            target_dirs.append(target_path)
            print(f"找到target目录: {target_path}")

    # 删除所有找到的target目录
    for target_dir in target_dirs:
        try:
            shutil.rmtree(target_dir)
            print(f"已删除: {target_dir}")
        except Exception as e:
            print(f"删除失败 {target_dir}: {e}")

    print(f"总共删除了 {len(target_dirs)} 个target目录")

if __name__ == "__main__":
    # 指定要遍历的根目录
    root_directory = "/Users/liwen/IdeaProjects/zi-object/"

    # 检查目录是否存在
    if not os.path.exists(root_directory):
        print(f"错误: 目录 {root_directory} 不存在")
        exit(1)

    if not os.path.isdir(root_directory):
        print(f"错误: {root_directory} 不是一个目录")
        exit(1)

    # 执行删除操作
    remove_target_dirs(root_directory)
