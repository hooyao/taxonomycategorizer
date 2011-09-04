package com.gemalto.tools.model;

import java.util.UUID;


/**
 * @author Hu Yao
 *
 */
public class VoteModel
{
	private String disc=""; //$NON-NLS-1$
	private String title=""; //$NON-NLS-1$
	private UUID uid = UUID.randomUUID();

	private String[] options;
	/**
	 * @param discp
	 * @param tit
	 * @param id
	 * @param op
	 */
	public VoteModel(String discp,String tit,String[] op)
	{
		this.disc = discp;
		this.title = tit;
		this.options  =op;
	}
	public VoteModel()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return description
	 */
	public String getDisc()
	{
		return this.disc;
	}
	/**
	 * @param disc
	 */
	public void setDisc(String disc)
	{
		this.disc = disc;
	}
	/**
	 * @return title
	 */
	public String getTitle()
	{
		return this.title;
	}
	/**
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	/**
	 * @return option array
	 */
	public String[] getOptions()
	{
		return this.options;
	}
	/**
	 * @param options
	 */
	public void setOptions(String[] options)
	{
		this.options = options;
	}
	/**
	 * @return UUID
	 */
	public UUID getUid()
	{
		return this.uid;
	}

	public void setUid(UUID uid)
	{
		this.uid = uid;
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UUID:"+this.uid.toString()+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Title:"+this.title+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Disc:"+this.disc+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Ops:"); //$NON-NLS-1$
		if(this.options!=null)
		{
			for(int i=0;i<this.options.length;i++)
			{
				sb.append(this.options[i]+" "); //$NON-NLS-1$
			}
		}
		sb.append("\n"); //$NON-NLS-1$
		return sb.toString();
	}
	
	
	
}
