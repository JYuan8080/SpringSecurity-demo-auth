/**
 * @name: CookieUtil
 * @author:  JYuan
 * @date: 2021/8/21 12:34
 * @description：CookieUtil
 * @update: 2021/8/21 12:34
 */
import Cookies from 'js-cookie'

const name = 'AUTH_TOKEN'

export default () => {
  return Cookies.get(name)
}
