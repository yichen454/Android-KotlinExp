# Android-KotlinExp
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/yichen454/Android-KotlinExp/blob/master/LICENSE)

# 项目结构
<div align=center><img width="450" src="https://raw.githubusercontent.com/yichen454/Android-KotlinExp/master/doc/structure.png"/></div>
app作为主applicaition Common作为所有Module的基类内含基本的组件封装（activity fragment等）常用工具类，每个Module通过Provider中的router与app进行关联
并以字节码插桩的形式进行依赖
