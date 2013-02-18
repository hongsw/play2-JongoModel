package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.fasterxml.jackson.annotation.*;
import plugins.JongoPlugin;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "_class")
public class JongoModel {

	//@JsonProperty("_id")
	public ObjectId _id;

	public void update() {
		this.remove();
		this.save();
	}

	public void save() {
		models().save(this);
	}

	public void remove() {
		models().remove(this._id);
	}

	public MongoCollection models() {
		String collectionName = this.getClass().getName() + "s";
		return JongoPlugin.getJongoPlugin().getJongo()
				.getCollection(collectionName);
	}

	public static class Finder<T> {

		private final Class<T> type;

		public Finder(Class<T> type) {
			this.type = type;
		}

		public MongoCollection models() {
			String collectionName = type.getName() + "s";
			return JongoPlugin.getJongoPlugin().getJongo()
					.getCollection(collectionName);
		}

		public List<T> all() {

			List<T> list = new ArrayList<T>();
			Iterator<T> it = models().find().as(type).iterator();
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list;
		}

		public T byId(ObjectId id) {
			return models().findOne(id).as(type);
		}

		public T byUrl(String url) {
			return models().findOne("{url: #}", url).as(type);
		}

		public List<T> byQuery(String query) {
			List<T> list = new ArrayList<T>();
			Iterator<T> it = models().find(query).as(type).iterator();
			while (it.hasNext()) {
				list.add(it.next());
			}
			return list;
		}
	}

	// public static Guestbook findByName(String name) {
	// return guestbooks().findOne("{name: #}", name).as(Guestbook.class);
	// }
	//
	// public static Guestbook findbyIDPW(Long id2, String password2) {
	// return guestbooks().findOne(
	// "{email: " + id2 + ", password:  "
	// + DigestUtils.md5Hex(password2) + "}").as(
	// Guestbook.class);
	// }
	//
	// public static List<Guestbook> findall() {
	// List<Guestbook> list = new ArrayList<Guestbook>();
	// Iterator<Guestbook> it = guestbooks().find().as(Guestbook.class)
	// .iterator();
	// while (it.hasNext()) {
	// list.add(it.next());
	// }
	// return list;
	// }
}
