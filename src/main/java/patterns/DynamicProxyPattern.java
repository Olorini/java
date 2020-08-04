package patterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyPattern {

	public void main(String[] args) {
		IPersonBean personBean = new PersonBeanImpl();
		IPersonBean ownerPersonBean = getOwnerProxy(personBean);
		ownerPersonBean.setHotOrNotRating(2);
		ownerPersonBean.getHotOrNotRating();
		ownerPersonBean.setName("dddd");
		ownerPersonBean.getName();
	}

	interface IPersonBean {
		String getName();
		int getHotOrNotRating();
		void setName(String name);
		void setHotOrNotRating(int rating);
	}

	static class PersonBeanImpl implements IPersonBean {
		private String name;
		private int ratingCount;
		private int rating;
		@Override
		public String getName() {
			return name;
		}
		@Override
		public int getHotOrNotRating() {
			return (ratingCount == 0) ? 0 : rating / ratingCount;
		}
		@Override
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public void setHotOrNotRating(int rating) {
			this.rating += rating;
			ratingCount ++;
		}
	}

	static class OwnerInvocationHandler implements InvocationHandler {
		private IPersonBean personBean;

		public OwnerInvocationHandler(IPersonBean personBean) {
			this.personBean = personBean;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				if (method.getName().startsWith("get")) {
					return method.invoke(personBean, args);
				} else if (method.getName().equals("setHotOrNotRating")) {
					throw new IllegalAccessException();
				} else if (method.getName().startsWith("set")) {
					return method.invoke(personBean, args);
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	IPersonBean getOwnerProxy(IPersonBean personBean) {
		return (IPersonBean) Proxy.newProxyInstance(
				personBean.getClass().getClassLoader(),
				personBean.getClass().getInterfaces(),
				new OwnerInvocationHandler(personBean)
		);
	}

}
