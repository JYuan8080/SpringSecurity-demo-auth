/**
 * @name: resValidator
 * @author:  JYuan
 * @date: 2021/8/20 22:57
 * @description：后端返回值校验器
 * @update: 2021/8/20 22:57
 */
import Message from 'element-plus/es/el-message'

export default (result, callback) => {
  if (result.code === 200) {
    callback(result.data)
    return true
  }
  Message.error(result.message)
  return false
}
